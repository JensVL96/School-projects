package tdt4250.transportation.main;

import java.util.List;

import org.eclipse.acceleo.query.delegates.AQLValidationDelegate;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator.ValidationDelegate;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tdt4250.transportation.Goods;
import tdt4250.transportation.TransportationFactory;
import tdt4250.transportation.TransportationPackage;
import tdt4250.transportation.VehicleRoute;
import tdt4250.transportation.util.GeoLoc;
import tdt4250.transportation.util.TransportationResourceFactoryImpl;

public class TransportationTest {

	@Before
	public void registerEMFStuff() {
		// registers the Transportation model's package object on its unique URI
		EPackage.Registry.INSTANCE.put(TransportationPackage.eNS_URI, TransportationPackage.eINSTANCE);
		// register our Resource factory for the xmi extension, so the right Resource implementation is used
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xmi", new TransportationResourceFactoryImpl());
		// register AQL (an OCL implementation) constraint support
		ValidationDelegate.Registry.INSTANCE.put("http://www.eclipse.org/acceleo/query/1.0", new AQLValidationDelegate());
	}

	private List<EObject> loadResource(String name) {
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createURI(getClass().getResource(name).toString());
		Resource resource = resourceSet.getResource(uri, true);
		EList<EObject> contents = resource.getContents();
		Assert.assertTrue(resource.getErrors().isEmpty());
		return contents;
	}

	@Test
	public void testValidation1() {
		for (EObject root : loadResource("validation1.xmi")) {
			Diagnostic diagnostics = Diagnostician.INSTANCE.validate(root);
			Assert.assertTrue(diagnostics.getSeverity() == Diagnostic.OK);
		}
	}

	@Test
	public void testValidation2() {
		for (EObject root : loadResource("validation2.xmi")) {
			Diagnostic diagnostics = Diagnostician.INSTANCE.validate(root);
			Assert.assertFalse(diagnostics.getSeverity() == Diagnostic.OK);
		}
	}

	@Test
	public void convertGeoLoc2String() {
		GeoLoc geoLoc = new GeoLoc(10, 63);
		EDataType dataType = TransportationPackage.eINSTANCE.getEGeoLoc();
		String s = TransportationFactory.eINSTANCE.convertToString(dataType, geoLoc);
		Assert.assertEquals("10.0 63.0", s);
		Object o = TransportationFactory.eINSTANCE.createFromString(dataType, s);
		Assert.assertEquals(geoLoc, o);
	}
	
	@Test
	public void testTotalWeight() {
		VehicleRoute vr = TransportationFactory.eINSTANCE.createVehicleRoute();
		Goods g1 = TransportationFactory.eINSTANCE.createGoods();
		g1.setWeight(100);
		Goods g2 = TransportationFactory.eINSTANCE.createGoods();
		g2.setWeight(200);
		vr.getGoods().add(g1);
		vr.getGoods().add(g2);
		Assert.assertTrue(vr.getTotalWeight() == 300);
		Assert.assertEquals(300.0, vr.eGet(TransportationPackage.eINSTANCE.getVehicleRoute_TotalWeight()));
	}
	
	@Test
	public void testVehicleRouteReceiveTrackingEvent() {
		// TODO
	}
}
