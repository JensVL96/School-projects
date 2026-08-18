/**
 */
package no.hal.quiz.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import no.hal.quiz.BooleanAnswer;
import no.hal.quiz.CharStyle;
import no.hal.quiz.ManyOptionsAnswer;
import no.hal.quiz.NumberAnswer;
import no.hal.quiz.NumberRange;
import no.hal.quiz.Option;
import no.hal.quiz.OptionsAnswer;
import no.hal.quiz.QA;
import no.hal.quiz.QARef;
import no.hal.quiz.Quiz;
import no.hal.quiz.QuizFactory;
import no.hal.quiz.QuizPackage;
import no.hal.quiz.QuizPart;
import no.hal.quiz.QuizPartRef;
import no.hal.quiz.SingleOptionsAnswer;
import no.hal.quiz.StringAnswer;
import no.hal.quiz.StringQuestion;
import no.hal.quiz.StyledString;
import no.hal.quiz.StyledStringQuestion;
import no.hal.quiz.Xml;
import no.hal.quiz.XmlAnswer;
import no.hal.quiz.XmlAttribute;
import no.hal.quiz.XmlContents;
import no.hal.quiz.XmlPIAnswerElement;
import no.hal.quiz.XmlQuestion;
import no.hal.quiz.XmlTag;
import no.hal.quiz.XmlTagElement;
import no.hal.quiz.util.RGB;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class QuizFactoryImpl extends EFactoryImpl implements QuizFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static QuizFactory init() {
		try {
			QuizFactory theQuizFactory = (QuizFactory)EPackage.Registry.INSTANCE.getEFactory(QuizPackage.eNS_URI);
			if (theQuizFactory != null) {
				return theQuizFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new QuizFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QuizFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case QuizPackage.QUIZ: return createQuiz();
			case QuizPackage.QUIZ_PART_REF: return createQuizPartRef();
			case QuizPackage.QUIZ_PART: return createQuizPart();
			case QuizPackage.QA_REF: return createQARef();
			case QuizPackage.QA: return createQA();
			case QuizPackage.STRING_QUESTION: return createStringQuestion();
			case QuizPackage.STYLED_STRING_QUESTION: return createStyledStringQuestion();
			case QuizPackage.STYLED_STRING: return createStyledString();
			case QuizPackage.CHAR_STYLE: return createCharStyle();
			case QuizPackage.XML_QUESTION: return createXmlQuestion();
			case QuizPackage.STRING_ANSWER: return createStringAnswer();
			case QuizPackage.NUMBER_ANSWER: return createNumberAnswer();
			case QuizPackage.NUMBER_RANGE: return createNumberRange();
			case QuizPackage.BOOLEAN_ANSWER: return createBooleanAnswer();
			case QuizPackage.XML_ANSWER: return createXmlAnswer();
			case QuizPackage.OPTIONS_ANSWER: return createOptionsAnswer();
			case QuizPackage.OPTION: return createOption();
			case QuizPackage.SINGLE_OPTIONS_ANSWER: return createSingleOptionsAnswer();
			case QuizPackage.MANY_OPTIONS_ANSWER: return createManyOptionsAnswer();
			case QuizPackage.XML: return createXml();
			case QuizPackage.XML_CONTENTS: return createXmlContents();
			case QuizPackage.XML_PI_ANSWER_ELEMENT: return createXmlPIAnswerElement();
			case QuizPackage.XML_TAG_ELEMENT: return createXmlTagElement();
			case QuizPackage.XML_TAG: return createXmlTag();
			case QuizPackage.XML_ATTRIBUTE: return createXmlAttribute();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case QuizPackage.RGB:
				return createRGBFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case QuizPackage.RGB:
				return convertRGBToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Quiz createQuiz() {
		QuizImpl quiz = new QuizImpl();
		return quiz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QuizPartRef createQuizPartRef() {
		QuizPartRefImpl quizPartRef = new QuizPartRefImpl();
		return quizPartRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QuizPart createQuizPart() {
		QuizPartImpl quizPart = new QuizPartImpl();
		return quizPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QARef createQARef() {
		QARefImpl qaRef = new QARefImpl();
		return qaRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QA createQA() {
		QAImpl qa = new QAImpl();
		return qa;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringQuestion createStringQuestion() {
		StringQuestionImpl stringQuestion = new StringQuestionImpl();
		return stringQuestion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StyledStringQuestion createStyledStringQuestion() {
		StyledStringQuestionImpl styledStringQuestion = new StyledStringQuestionImpl();
		return styledStringQuestion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StyledString createStyledString() {
		StyledStringImpl styledString = new StyledStringImpl();
		return styledString;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CharStyle createCharStyle() {
		CharStyleImpl charStyle = new CharStyleImpl();
		return charStyle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XmlQuestion createXmlQuestion() {
		XmlQuestionImpl xmlQuestion = new XmlQuestionImpl();
		return xmlQuestion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringAnswer createStringAnswer() {
		StringAnswerImpl stringAnswer = new StringAnswerImpl();
		return stringAnswer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NumberAnswer createNumberAnswer() {
		NumberAnswerImpl numberAnswer = new NumberAnswerImpl();
		return numberAnswer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NumberRange createNumberRange() {
		NumberRangeImpl numberRange = new NumberRangeImpl();
		return numberRange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BooleanAnswer createBooleanAnswer() {
		BooleanAnswerImpl booleanAnswer = new BooleanAnswerImpl();
		return booleanAnswer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XmlAnswer createXmlAnswer() {
		XmlAnswerImpl xmlAnswer = new XmlAnswerImpl();
		return xmlAnswer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OptionsAnswer createOptionsAnswer() {
		OptionsAnswerImpl optionsAnswer = new OptionsAnswerImpl();
		return optionsAnswer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Option createOption() {
		OptionImpl option = new OptionImpl();
		return option;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SingleOptionsAnswer createSingleOptionsAnswer() {
		SingleOptionsAnswerImpl singleOptionsAnswer = new SingleOptionsAnswerImpl();
		return singleOptionsAnswer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ManyOptionsAnswer createManyOptionsAnswer() {
		ManyOptionsAnswerImpl manyOptionsAnswer = new ManyOptionsAnswerImpl();
		return manyOptionsAnswer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Xml createXml() {
		XmlImpl xml = new XmlImpl();
		return xml;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XmlContents createXmlContents() {
		XmlContentsImpl xmlContents = new XmlContentsImpl();
		return xmlContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XmlPIAnswerElement createXmlPIAnswerElement() {
		XmlPIAnswerElementImpl xmlPIAnswerElement = new XmlPIAnswerElementImpl();
		return xmlPIAnswerElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XmlTagElement createXmlTagElement() {
		XmlTagElementImpl xmlTagElement = new XmlTagElementImpl();
		return xmlTagElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XmlTag createXmlTag() {
		XmlTagImpl xmlTag = new XmlTagImpl();
		return xmlTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XmlAttribute createXmlAttribute() {
		XmlAttributeImpl xmlAttribute = new XmlAttributeImpl();
		return xmlAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RGB createRGBFromString(EDataType eDataType, String initialValue) {
		return (RGB)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertRGBToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public QuizPackage getQuizPackage() {
		return (QuizPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static QuizPackage getPackage() {
		return QuizPackage.eINSTANCE;
	}

} //QuizFactoryImpl
