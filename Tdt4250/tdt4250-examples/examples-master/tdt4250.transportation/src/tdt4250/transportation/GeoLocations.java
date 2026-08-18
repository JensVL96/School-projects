/**
 */
package tdt4250.transportation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Geo Locations</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.transportation.GeoLocations#getLocations <em>Locations</em>}</li>
 * </ul>
 *
 * @see tdt4250.transportation.TransportationPackage#getGeoLocations()
 * @model
 * @generated
 */
public interface GeoLocations extends EObject {
	/**
	 * Returns the value of the '<em><b>Locations</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.transportation.GeoLocation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locations</em>' containment reference list.
	 * @see tdt4250.transportation.TransportationPackage#getGeoLocations_Locations()
	 * @model containment="true"
	 * @generated
	 */
	EList<GeoLocation> getLocations();

} // GeoLocations
