package com.example.soap_processing;

import com.example.soap_annotations.SoapRequest;
import com.example.soap_annotations.SoapResponse;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

public class SoapProcessor extends AbstractProcessor {
	
	static final ClassName classElementAnnotation = ClassName.get("org.simpleframework.xml", "Element");
	static final ClassName classNamespaceAnnotation = ClassName.get("org.simpleframework.xml", "Namespace");
	static final ClassName classRootAnnotation = ClassName.get("org.simpleframework.xml", "Root");
	static final ClassName classNameEnvelopeHeader =
		ClassName.get("com.example.bookingagent.data.model", "EnvelopeHeader");
	
	private Filer filer;
	private Messager messager;
	private Elements elements;
	private AnnotationSpec namespaceAnnotation;
	private AnnotationSpec rootBodyAnnotation;
	private AnnotationSpec rootEnvelopeAnnotation;
	
	@Override
	public synchronized void init(ProcessingEnvironment processingEnvironment) {
		super.init(processingEnvironment);
		filer = processingEnvironment.getFiler();
		messager = processingEnvironment.getMessager();
		elements = processingEnvironment.getElementUtils();
	}
	
	@Override
	public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
		
		setupBasicAnnotations();
		
		for (Element element : roundEnvironment.getElementsAnnotatedWith(SoapRequest.class)) {
			
			checkAnnotationFitsItsKind(element);
			
			TypeElement typeElement = (TypeElement) element;
			
			// Annotated class info
			String classSimpleName = typeElement.getSimpleName().toString();
			String packageLocation = elements.getPackageOf(typeElement).getQualifiedName().toString();
			
			// Annotated class type
			ClassName classType = ClassName.get(packageLocation, classSimpleName);
			
			try {
				createEnvelopeBodyRequest(classSimpleName, packageLocation, classType);
				createEnvelopeRequest(classSimpleName, packageLocation, classType);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for (Element element : roundEnvironment.getElementsAnnotatedWith(SoapResponse.class)) {
			
			checkAnnotationFitsItsKind(element);
			
			TypeElement typeElement = (TypeElement) element;
			
			// Annotated class info
			String classSimpleName = typeElement.getSimpleName().toString();
			String packageLocation = elements.getPackageOf(typeElement).getQualifiedName().toString();
			
			// Annotated class type
			ClassName classType = ClassName.get(packageLocation, classSimpleName);
			
			try {
				createEnvelopeBodyResponse(classSimpleName, packageLocation, classType);
				createEnvelopeResponse(classSimpleName, packageLocation, classType);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	private void createEnvelopeResponse(String classSimpleName, String packageLocation, ClassName classType)
		throws IOException {
		ClassName envelopeBodyClass = ClassName.get(packageLocation, classSimpleName + "EnvelopeBody");
		
		// body element annotation
		AnnotationSpec bodyElementAnnotation =
			createAnnotationWithTwoFields(classElementAnnotation, "name", "\"" + "Body" + "\"", "required", "false");
		
		// header element annotation
		AnnotationSpec headerElementAnnotation =
			createAnnotationWithTwoFields(classElementAnnotation, "name", "\"" + "Header" + "\"", "required", "false");
		
		// Class
		TypeSpec.Builder envelopeClass = TypeSpec.classBuilder("Envelope" + classSimpleName)
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(rootEnvelopeAnnotation)
			.addAnnotation(namespaceAnnotation);
		
		// body field
		FieldSpec bodyField =
			FieldSpec.builder(envelopeBodyClass, "body", Modifier.PRIVATE).addAnnotation(bodyElementAnnotation).build();
		
		// header field
		FieldSpec headerField = FieldSpec.builder(classNameEnvelopeHeader, "header", Modifier.PRIVATE)
			.addAnnotation(headerElementAnnotation)
			.build();
		
		// Get body Method
		MethodSpec getBody =
			createMethodWithoutParameters("getBody", Modifier.PUBLIC, envelopeBodyClass, "return " + "body");
		
		// Set body Method
		MethodSpec setBody =
			createMethodWithParameters("setBody", Modifier.PUBLIC, TypeName.VOID, "this.body = body",
				envelopeBodyClass,
				"body");
		
		// Get header Method
		MethodSpec getHeader =
			createMethodWithoutParameters("getHeader", Modifier.PUBLIC, classNameEnvelopeHeader, "return " + "header");
		
		// Set header Method
		MethodSpec setHeader =
			createMethodWithParameters("setHeader", Modifier.PUBLIC, TypeName.VOID, "this.header = header",
				classNameEnvelopeHeader, "header");
		
		createMethodWithoutParameters("getHeader", Modifier.PUBLIC, classNameEnvelopeHeader, "return " + "header");
		
		envelopeClass.addField(bodyField)
			.addField(headerField)
			.addMethod(getBody)
			.addMethod(setBody)
			.addMethod(getHeader)
			.addMethod(setHeader);
		
		JavaFile.builder(packageLocation, envelopeClass.build()).build().writeTo(filer);
	}
	
	private void setupBasicAnnotations() {
		// Namespace annotation
		namespaceAnnotation =
			createAnnotationWithTwoFields(classNamespaceAnnotation, "prefix", "\"" + "SOAP-ENV" + "\"", "reference",
				"\"" + "http://schemas.xmlsoap.org/soap/envelope/" + "\"");
		
		// Root Body Annotation
		rootBodyAnnotation = AnnotationSpec.builder(classRootAnnotation).build();
		
		rootEnvelopeAnnotation =
			AnnotationSpec.builder(classRootAnnotation).addMember("name", "\"" + "Envelope" + "\"").build();
	}
	
	private void createEnvelopeRequest(String classSimpleName, String packageLocation, ClassName classType)
		throws IOException {
		
		ClassName envelopeBodyClass = ClassName.get(packageLocation, classSimpleName + "EnvelopeBody");
		
		// body element annotation
		AnnotationSpec bodyElementAnnotation =
			createAnnotationWithTwoFields(classElementAnnotation, "name", "\"" + "Body" + "\"", "required", "false");
		
		// header element annotation
		AnnotationSpec headerElementAnnotation =
			createAnnotationWithTwoFields(classElementAnnotation, "name", "\"" + "Header" + "\"", "required", "false");
		
		// Class
		TypeSpec.Builder envelopeClass = TypeSpec.classBuilder("Envelope" + classSimpleName)
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(rootEnvelopeAnnotation)
			.addAnnotation(namespaceAnnotation);
		
		// body field
		FieldSpec bodyField =
			FieldSpec.builder(envelopeBodyClass, "body", Modifier.PRIVATE).addAnnotation(bodyElementAnnotation).build();
		
		// header field
		FieldSpec headerField = FieldSpec.builder(classNameEnvelopeHeader, "header", Modifier.PRIVATE)
			.addAnnotation(headerElementAnnotation)
			.build();
		
		// Constructor
		MethodSpec constructor = createConstructorWithParameter(Modifier.PUBLIC, classType, "data", "this.body"
			+ " = new "
			+ classSimpleName
			+ "EnvelopeBody(data);\n"
			+ "this.header = new EnvelopeHeader"
			+ "()");
		
		// Get body Method
		MethodSpec getBody =
			createMethodWithoutParameters("getBody", Modifier.PUBLIC, envelopeBodyClass, "return " + "body");
		
		// Get header Method
		MethodSpec getHeader =
			createMethodWithoutParameters("getHeader", Modifier.PUBLIC, classNameEnvelopeHeader, "return " + "header");
		
		// Set header Method
		MethodSpec setHeader =
			createMethodWithParameters("setHeader", Modifier.PUBLIC, TypeName.VOID, "this.header = header",
				classNameEnvelopeHeader, "header");
		
		createMethodWithoutParameters("getHeader", Modifier.PUBLIC, classNameEnvelopeHeader, "return " + "header");
		
		envelopeClass.addMethod(constructor)
			.addField(bodyField)
			.addField(headerField)
			.addMethod(getBody)
			.addMethod(getHeader)
			.addMethod(setHeader);
		
		JavaFile.builder(packageLocation, envelopeClass.build()).build().writeTo(filer);
	}
	
	private void createEnvelopeBodyRequest(String classSimpleName, String packageLocation, ClassName classType)
		throws IOException {
		
		// Element Annotation
		AnnotationSpec elementAnnotation =
			createAnnotationWithTwoFields(classElementAnnotation, "name", "\"" + classSimpleName + "\"", "required",
				"false");
		
		// Class
		TypeSpec.Builder bodyClass = TypeSpec.classBuilder(classSimpleName + "EnvelopeBody")
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(rootBodyAnnotation)
			.addAnnotation(namespaceAnnotation);
		
		// Data field
		FieldSpec dataFiled =
			FieldSpec.builder(classType, "data", Modifier.PRIVATE).addAnnotation(elementAnnotation).build();
		
		// Constructor
		MethodSpec constructor =
			createConstructorWithParameter(Modifier.PUBLIC, classType, "data", "this.data" + " = data");
		
		// Get Method
		MethodSpec getBody = createMethodWithoutParameters("getBody", Modifier.PUBLIC, classType, "return data");
		
		bodyClass.addField(dataFiled);
		bodyClass.addMethod(constructor);
		bodyClass.addMethod(getBody);
		
		JavaFile.builder(packageLocation, bodyClass.build()).build().writeTo(filer);
	}
	
	private void createEnvelopeBodyResponse(String classSimpleName, String packageLocation, ClassName classType)
		throws IOException {
		
		// Element Annotation
		AnnotationSpec elementAnnotation =
			createAnnotationWithTwoFields(classElementAnnotation, "name", "\"" + classSimpleName + "\"", "required",
				"false");
		
		// Class
		TypeSpec.Builder bodyClass = TypeSpec.classBuilder(classSimpleName + "EnvelopeBody")
			.addModifiers(Modifier.PUBLIC)
			.addAnnotation(rootBodyAnnotation)
			.addAnnotation(namespaceAnnotation);
		
		// Data field
		FieldSpec dataFiled =
			FieldSpec.builder(classType, "data", Modifier.PRIVATE).addAnnotation(elementAnnotation).build();
		
		// Get Method
		MethodSpec getBody = createMethodWithoutParameters("getBody", Modifier.PUBLIC, classType, "return data");
		
		// Set Method
		MethodSpec setBody =
			createMethodWithParameters("setBody", Modifier.PUBLIC, ClassName.VOID, "this.data" + " = data", classType,
				"data");
		
		bodyClass.addField(dataFiled);
		bodyClass.addMethod(getBody);
		bodyClass.addMethod(setBody);
		
		JavaFile.builder(packageLocation, bodyClass.build()).build().writeTo(filer);
	}
	
	private AnnotationSpec createAnnotationWithTwoFields(ClassName annotationName, String fieldOneName,
		String fieldOneValue, String fieldTwoName, String fieldTwoValue) {
		
		return AnnotationSpec.builder(annotationName)
			.addMember(fieldOneName, fieldOneValue)
			.addMember(fieldTwoName, fieldTwoValue)
			.build();
	}
	
	private MethodSpec createMethodWithParameters(String methodName, Modifier visibility, TypeName returnType,
		String statement, ClassName parameterType, String parameterName) {
		
		return MethodSpec.methodBuilder(methodName)
			.addModifiers(visibility)
			.returns(returnType)
			.addParameter(parameterType, parameterName)
			.addStatement(statement)
			.build();
	}
	
	private MethodSpec createMethodWithoutParameters(String methodName, Modifier visibility, ClassName returnType,
		String statement) {
		
		return MethodSpec.methodBuilder(methodName)
			.addModifiers(visibility)
			.returns(returnType)
			.addStatement(statement)
			.build();
	}
	
	private MethodSpec createConstructorWithParameter(Modifier visibility, ClassName parameterType,
		String parameterName, String statement) {
		
		return MethodSpec.constructorBuilder()
			.addModifiers(visibility)
			.addParameter(parameterType, parameterName)
			.addStatement(statement)
			.build();
	}
	
	private void checkAnnotationFitsItsKind(Element element) {
		if (element.getKind() != ElementKind.CLASS) {
			messager.printMessage(Diagnostic.Kind.ERROR, "Annotation applied on wrong element");
			return;
		}
	}
	
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		
		Set<String> annotations = new LinkedHashSet<>();
		annotations.add(SoapRequest.class.getCanonicalName());
		annotations.add(SoapResponse.class.getCanonicalName());
		return annotations;
	}
}
