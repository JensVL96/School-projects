/**
 */
package tdt4250.transportation;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Goods</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link tdt4250.transportation.Goods#getWeight <em>Weight</em>}</li>
 *   <li>{@link tdt4250.transportation.Goods#getOrigin <em>Origin</em>}</li>
 *   <li>{@link tdt4250.transportation.Goods#getDestination <em>Destination</em>}</li>
 * </ul>
 *
 * @see tdt4250.transportation.TransportationPackage#getGoods()
 * @model
 * @generated
 */
public interface Goods extends EObject {
	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see #setWeight(double)
	 * @see tdt4250.transportation.TransportationPackage#getGoods_Weight()
	 * @model
	 * @generated
	 */
	double getWeight();

	/**
	 * Sets the value of the '{@link tdt4250.transportation.Goods#getWeight <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Weight</em>' attribute.
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(double value);

	/**
	 * Returns the value of the '<em><b>Origin</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origin</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origin</em>' reference.
	 * @see #setOrigin(GeoLocation)
	 * @see tdt4250.transportation.TransportationPackage#getGoods_Origin()
	 * @model
	 * @generated
	 */
	GeoLocation getOrigin();

	/**
	 * Sets the value of the '{@link tdt4250.transportation.Goods#getOrigin <em>Origin</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origin</em>' reference.
	 * @see #getOrigin()
	 * @generated
	 */
	void setOrigin(GeoLocation value);

	/**
	 * Returns the value of the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destination</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination</em>' reference.
	 * @see #setDestination(GeoLocation)
	 * @see tdt4250.transportation.TransportationPackage#getGoods_Destination()
	 * @model
	 * @generated
	 */
	GeoLocation getDestination();

	/**
	 * Sets the value of the '{@link tdt4250.transportation.Goods#getDestination <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination</em>' reference.
	 * @see #getDestination()
	 * @generated
	 */
	void setDestination(GeoLocation value);

} // Goods
