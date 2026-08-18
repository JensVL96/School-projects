/**
 */
package tdt4250.ra.util;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import tdt4250.ra.Course;
import tdt4250.ra.Department;
import tdt4250.ra.Person;
import tdt4250.ra.RaFactory;
import tdt4250.ra.ResourceAllocation;

/**
 * <!-- begin-user-doc -->
 * The <b>Resource </b> associated with the package.
 * <!-- end-user-doc -->
 * @see tdt4250.ra.util.RaResourceFactoryImpl
 * @generated
 */
public class RaResourceImpl extends XMIResourceImpl {
	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param uri the URI of the new resource.
	 * @generated
	 */
	public RaResourceImpl(URI uri) {
		super(uri);
	}

	public static void main(String[] args) throws IOException {
		ResourceSet resSet = new ResourceSetImpl();
		resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ra", new RaResourceFactoryImpl());
		Resource raResource = resSet.createResource(URI.createURI("test.ra"));
		Department dep = RaFactory.eINSTANCE.createDepartment();
		dep.setName("Dept. of CS");
		dep.setShortName("IDI");
		raResource.getContents().add(dep);

		Person p1 = RaFactory.eINSTANCE.createPerson();
		p1.setName("Hallvard");
		dep.getStaff().add(p1);
		Course c1 = RaFactory.eINSTANCE.createCourse();
		c1.setCode("TDT4250");
		c1.setName("Advanced Software Design");
		Course c2 = RaFactory.eINSTANCE.createCourse();
		c2.setCode("IT1901");
		c2.setName("IT project");
		dep.getCourses().add(c1);
		dep.getCourses().add(c2);
		ResourceAllocation ra1 = RaFactory.eINSTANCE.createResourceAllocation();
		ra1.setPerson(p1);
		ra1.setCourse(c1);
		ra1.setFactor(0.5f);
		dep.getResourceAllocations().add(ra1);
		ResourceAllocation ra2 = RaFactory.eINSTANCE.createResourceAllocation();
		ra1.setPerson(p1);
		ra1.setCourse(c2);
		ra1.setFactor(0.8f);
		dep.getResourceAllocations().add(ra2);
		
		raResource.save(System.out, null);
	}
	
} //RaResourceImpl
