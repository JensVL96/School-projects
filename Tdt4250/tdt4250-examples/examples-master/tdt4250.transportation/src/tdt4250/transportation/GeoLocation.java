/**
 */
package tdt4250.transportation;

import org.eclipse.emf.ecore.EObject;

import tdt4250.transportation.util.GeoLoc;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Geo Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.transportation.GeoLocation#getName <em>Name</em>}</li>
 *   <li>{@link tdt4250.transportation.GeoLocation#getGeoLoc <em>Geo Loc</em>}</li>
 * </ul>
 *
 * @see tdt4250.transportation.TransportationPackage#getGeoLocation()
 * @model
 * @generated
 */
public interface GeoLocation extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see tdt4250.transportation.TransportationPackage#getGeoLocation_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link tdt4250.transportation.GeoLocation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Geo Loc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Geo Loc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Geo Loc</em>' attribute.
	 * @see #setGeoLoc(GeoLoc)
	 * @see tdt4250.transportation.TransportationPackage#getGeoLocation_GeoLoc()
	 * @model dataType="tdt4250.transportation.EGeoLoc"
	 * @generated
	 */
	GeoLoc getGeoLoc();

	/**
	 * Sets the value of the '{@link tdt4250.transportation.GeoLocation#getGeoLoc <em>Geo Loc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Geo Loc</em>' attribute.
	 * @see #getGeoLoc()
	 * @generated
	 */
	void setGeoLoc(GeoLoc value);

} // GeoLocation
