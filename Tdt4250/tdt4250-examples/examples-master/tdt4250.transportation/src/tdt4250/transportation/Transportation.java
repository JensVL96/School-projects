/**
 */
package tdt4250.transportation;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transportation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.transportation.Transportation#getVehicles <em>Vehicles</em>}</li>
 *   <li>{@link tdt4250.transportation.Transportation#getLocations <em>Locations</em>}</li>
 * </ul>
 *
 * @see tdt4250.transportation.TransportationPackage#getTransportation()
 * @model
 * @generated
 */
public interface Transportation extends EObject {
	/**
	 * Returns the value of the '<em><b>Vehicles</b></em>' containment reference list.
	 * The list contents are of type {@link tdt4250.transportation.Vehicle}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Vehicles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vehicles</em>' containment reference list.
	 * @see tdt4250.transportation.TransportationPackage#getTransportation_Vehicles()
	 * @model containment="true"
	 * @generated
	 */
	EList<Vehicle> getVehicles();

	/**
	 * Returns the value of the '<em><b>Locations</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locations</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locations</em>' containment reference.
	 * @see #setLocations(GeoLocation)
	 * @see tdt4250.transportation.TransportationPackage#getTransportation_Locations()
	 * @model containment="true"
	 * @generated
	 */
	GeoLocation getLocations();

	/**
	 * Sets the value of the '{@link tdt4250.transportation.Transportation#getLocations <em>Locations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locations</em>' containment reference.
	 * @see #getLocations()
	 * @generated
	 */
	void setLocations(GeoLocation value);

} // Transportation
