package tdt4250.transportation.main;

import org.eclipse.acceleo.query.delegates.AQLValidationDelegate;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator.ValidationDelegate;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;

import tdt4250.transportation.Transportation;
import tdt4250.transportation.TransportationFactory;
import tdt4250.transportation.TransportationPackage;
import tdt4250.transportation.Vehicle;
import tdt4250.transportation.util.TransportationResourceFactoryImpl;

public class TransportationMain {

	private static void registerEMFStuff() {
		// registers the Transportation model's package object on its unique URI
		EPackage.Registry.INSTANCE.put(TransportationPackage.eNS_PREFIX, TransportationPackage.eINSTANCE);
		// register our Resource factory for the xmi extension, so the right Resource implementation is used
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new TransportationResourceFactoryImpl());
		// register AQL (an OCL implementation) constraint support
		ValidationDelegate.Registry.INSTANCE.put("http://www.eclipse.org/acceleo/query/1.0", new AQLValidationDelegate());
	}
	
	public static void main(String[] args) {
		registerEMFStuff();

		// create objects using generated API
		// The factory (singleton) object is used for creating instances  
		TransportationFactory factory = TransportationFactory.eINSTANCE;
		Transportation t = factory.createTransportation();
		Vehicle v1 = factory.createVehicle();
		t.getVehicles().add(v1);
		Vehicle v2 = factory.createVehicle();
		t.getVehicles().add(v2);
		
		// create objects using generic API
		Vehicle v3 = (Vehicle) factory.create(TransportationPackage.eINSTANCE.getVehicle());
		t.getVehicles().add(v3);

		// create objects by loading file
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createURI(TransportationMain.class.getResource("Transportation.xmi").toString());
		Resource resource = resourceSet.getResource(uri, true);
		try {
			for (EObject root : resource.getContents()) {
				Diagnostic diagnostics = Diagnostician.INSTANCE.validate(root);
				if (diagnostics.getSeverity() != Diagnostic.OK) {
					System.err.println(diagnostics.getMessage());
					for (Diagnostic child : diagnostics.getChildren()) {
						System.err.println(child.getMessage());						
					}
				} else {
					System.out.println("No problems with " + root);
				}
			}
		} catch (RuntimeException e) {
			System.out.println(e);
		}
	}
}
