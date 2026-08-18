package tdt4250.servletsupport.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Stack;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferencePolicy;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import tdt4250.servletsupport.IResponseSerializer;

@SuppressWarnings("serial")
@Component
public class JsonSerializer extends StdSerializer<EObject> implements IResponseSerializer {
	
	private ObjectMapper objectMapper;
	
	public JsonSerializer() {
		super(EObject.class);
		SimpleModule module = new SimpleModule();
		module.addSerializer(EObject.class, this);
		this.objectMapper = new ObjectMapper();
		objectMapper.registerModule(module);
	}

	@Reference(policy=ReferencePolicy.DYNAMIC)
	protected volatile Collection<JsonEObjectSerializer> jsonEObjectSerializers;

	protected JsonEObjectSerializer getEObjectSerializer(EObject eObject) {
		if (jsonEObjectSerializers != null) {
			for (JsonEObjectSerializer serializer : jsonEObjectSerializers) {
				if (serializer.accept(eObject)) {
					return serializer;
				}
			}
		}
		return null;
	}
	
	@Reference(policy=ReferencePolicy.DYNAMIC)
	protected volatile Collection<JsonEAttributeSerializer> jsonEAttributeSerializers;
	
	protected JsonEAttributeSerializer getEAttributeSerializer(EObject eObject, EAttribute attr, Object value) {
		if (jsonEAttributeSerializers != null) {
			for (JsonEAttributeSerializer serializer : jsonEAttributeSerializers) {
				if (serializer.accept(eObject, attr, value)) {
					return serializer;
				}
			}
		}
		return null;
	}
	
	private Stack<EObject> occurStack = new Stack<EObject>();

	@Override
	public void serialize(Object object, Writer writer) throws Exception {
		try {
			this.occurStack.clear();
			ObjectWriter objectWriter = objectMapper.writer(new DefaultPrettyPrinter());
			objectWriter.writeValue(writer, object);
		} finally {
			this.occurStack.clear();
		}
	}
	
	public static final String JSON_SERIALIZER_ANNOTATION_SOURCE = JsonSerializer.class.getName();

	@Override
	public void serialize(EObject eObject, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
//		int count = 0;
//		for (int i = occurStack.size() - 1; i >= 0; i--) {
//			if (occurStack.get(i) == eObject) {
//				count++;
//				if (count >= 2) {
//					generator.writeString("...");
//					return;
//				}
//			}
//		}
		for (int i = occurStack.size() - 1; i >= 0; i--) {
			if (occurStack.get(i) == eObject) {
				generator.writeString("???");
				return;
			}
		}
		occurStack.push(eObject);
		try {
			JsonEObjectSerializer eObjectSerializer = getEObjectSerializer(eObject);
			if (eObjectSerializer != null) {
				eObjectSerializer.serialize(eObject, generator);
			} else {
				try {
					generator.writeStartObject();
					for (EStructuralFeature feature : eObject.eClass().getEAllStructuralFeatures()) {
						if (includeFeature(feature)) {
							serializeFeature(eObject, feature, generator);
						}
					}
					for (EOperation op : eObject.eClass().getEAllOperations()) {
						if (includeOperation(op)) {
							serializeOperation(eObject, op, generator);
						}
					}
				} finally {
					generator.writeEndObject();
				}
			}
		} finally {
//			System.err.println("Exception while serializing " + eObject + ":" + e);
			if (! occurStack.isEmpty()) {
				occurStack.pop();
			}
		}
	}

	protected boolean includeFeature(EStructuralFeature feature) {
		boolean include = false;
		if (feature instanceof EReference) {
			EReference ref = (EReference) feature;
			if (ref.isContainment()) {
				include = AnnotationUtil.includeTypedElement(feature, JSON_SERIALIZER_ANNOTATION_SOURCE, true);
			} else if (! ref.isContainer()) {
				include = AnnotationUtil.includeTypedElement(feature, JSON_SERIALIZER_ANNOTATION_SOURCE, false);
			}
		} else {
			include = AnnotationUtil.includeTypedElement(feature, JSON_SERIALIZER_ANNOTATION_SOURCE, true);							
		}
		return include;
	}
	
	private boolean excludeNullValues = true, excludeEmptyManyValues = true;
	
	protected void serializeFeature(EObject eObject, EStructuralFeature feature, JsonGenerator generator) throws IOException {
		String name = getFieldName(feature);
		Object value = null;
		try {
			value = eObject.eGet(feature);
			if (feature instanceof EAttribute) {
				EAttribute attr = (EAttribute) feature;
				JsonEAttributeSerializer attrSerializer = getEAttributeSerializer(eObject, attr, value);
				if (attrSerializer != null) {
					attrSerializer.serialize(eObject, attr, value, name, generator);
					return;
				}
			}
		} catch (Exception e) {
			value = "Exception when getting/serializing value of " + feature.getName() + ": " + e.getMessage();
		}
		if (excludeNullValues && value == null) {
			return;
		}
		if (excludeEmptyManyValues && feature.isMany() && value instanceof Collection<?> && ((Collection<?>) value).isEmpty()) {
			return;
		}
		generator.writeFieldName(name);
		generator.writeObject(value);
	}
	
	protected boolean includeOperation(EOperation op) {
		return op.getEParameters().isEmpty() && AnnotationUtil.includeTypedElement(op, JSON_SERIALIZER_ANNOTATION_SOURCE, false);
	}

	protected void serializeOperation(EObject eObject, EOperation op, JsonGenerator generator) throws IOException {
		String name = getFieldName(op);
		Object value = null;
		try {
			value = eObject.eInvoke(op, null);
		} catch (Exception e) {
			value = "Exception when invoking " + op.getName() + ": " + e.getMessage();
		}
		if (value != null) {
			generator.writeFieldName(name);
			generator.writeObject(value);
		}
	}

	protected String getFieldName(ENamedElement named) {
		String altName = EcoreUtil.getAnnotation(named, JSON_SERIALIZER_ANNOTATION_SOURCE, "name");
		return altName != null ? altName : named.getName();
	}
}
