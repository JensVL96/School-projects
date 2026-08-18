package tdt4250.ra.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import tdt4250.ra.services.Rax2GrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalRax2Parser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'-'", "'Persons:'", "'Courses:'", "'Allocations:'", "'requires'", "','", "'for'", "'.'", "'%'", "'is'", "'in'", "'as'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalRax2Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalRax2Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalRax2Parser.tokenNames; }
    public String getGrammarFileName() { return "InternalRax2.g"; }



     	private Rax2GrammarAccess grammarAccess;

        public InternalRax2Parser(TokenStream input, Rax2GrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Department";
       	}

       	@Override
       	protected Rax2GrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleDepartment"
    // InternalRax2.g:64:1: entryRuleDepartment returns [EObject current=null] : iv_ruleDepartment= ruleDepartment EOF ;
    public final EObject entryRuleDepartment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDepartment = null;


        try {
            // InternalRax2.g:64:51: (iv_ruleDepartment= ruleDepartment EOF )
            // InternalRax2.g:65:2: iv_ruleDepartment= ruleDepartment EOF
            {
             newCompositeNode(grammarAccess.getDepartmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleDepartment=ruleDepartment();

            state._fsp--;

             current =iv_ruleDepartment; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDepartment"


    // $ANTLR start "ruleDepartment"
    // InternalRax2.g:71:1: ruleDepartment returns [EObject current=null] : ( ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_staff_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* otherlv_7= 'Allocations:' ( (lv_resourceAllocations_8_0= ruleResourceAllocation ) )* ) ;
    public final EObject ruleDepartment() throws RecognitionException {
        EObject current = null;

        Token lv_shortName_0_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_staff_4_0 = null;

        EObject lv_courses_6_0 = null;

        EObject lv_resourceAllocations_8_0 = null;



        	enterRule();

        try {
            // InternalRax2.g:77:2: ( ( ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_staff_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* otherlv_7= 'Allocations:' ( (lv_resourceAllocations_8_0= ruleResourceAllocation ) )* ) )
            // InternalRax2.g:78:2: ( ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_staff_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* otherlv_7= 'Allocations:' ( (lv_resourceAllocations_8_0= ruleResourceAllocation ) )* )
            {
            // InternalRax2.g:78:2: ( ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_staff_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* otherlv_7= 'Allocations:' ( (lv_resourceAllocations_8_0= ruleResourceAllocation ) )* )
            // InternalRax2.g:79:3: ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_staff_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* otherlv_7= 'Allocations:' ( (lv_resourceAllocations_8_0= ruleResourceAllocation ) )*
            {
            // InternalRax2.g:79:3: ( (lv_shortName_0_0= RULE_ID ) )
            // InternalRax2.g:80:4: (lv_shortName_0_0= RULE_ID )
            {
            // InternalRax2.g:80:4: (lv_shortName_0_0= RULE_ID )
            // InternalRax2.g:81:5: lv_shortName_0_0= RULE_ID
            {
            lv_shortName_0_0=(Token)match(input,RULE_ID,FOLLOW_3); 

            					newLeafNode(lv_shortName_0_0, grammarAccess.getDepartmentAccess().getShortNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDepartmentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"shortName",
            						lv_shortName_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_1, grammarAccess.getDepartmentAccess().getHyphenMinusKeyword_1());
            		
            // InternalRax2.g:101:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalRax2.g:102:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalRax2.g:102:4: (lv_name_2_0= RULE_STRING )
            // InternalRax2.g:103:5: lv_name_2_0= RULE_STRING
            {
            lv_name_2_0=(Token)match(input,RULE_STRING,FOLLOW_5); 

            					newLeafNode(lv_name_2_0, grammarAccess.getDepartmentAccess().getNameSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getDepartmentRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.STRING");
            				

            }


            }

            otherlv_3=(Token)match(input,12,FOLLOW_6); 

            			newLeafNode(otherlv_3, grammarAccess.getDepartmentAccess().getPersonsKeyword_3());
            		
            // InternalRax2.g:123:3: ( (lv_staff_4_0= rulePerson ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_ID && LA1_0<=RULE_STRING)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalRax2.g:124:4: (lv_staff_4_0= rulePerson )
            	    {
            	    // InternalRax2.g:124:4: (lv_staff_4_0= rulePerson )
            	    // InternalRax2.g:125:5: lv_staff_4_0= rulePerson
            	    {

            	    					newCompositeNode(grammarAccess.getDepartmentAccess().getStaffPersonParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_staff_4_0=rulePerson();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDepartmentRule());
            	    					}
            	    					add(
            	    						current,
            	    						"staff",
            	    						lv_staff_4_0,
            	    						"tdt4250.ra.Rax2.Person");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            otherlv_5=(Token)match(input,13,FOLLOW_7); 

            			newLeafNode(otherlv_5, grammarAccess.getDepartmentAccess().getCoursesKeyword_5());
            		
            // InternalRax2.g:146:3: ( (lv_courses_6_0= ruleCourse ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalRax2.g:147:4: (lv_courses_6_0= ruleCourse )
            	    {
            	    // InternalRax2.g:147:4: (lv_courses_6_0= ruleCourse )
            	    // InternalRax2.g:148:5: lv_courses_6_0= ruleCourse
            	    {

            	    					newCompositeNode(grammarAccess.getDepartmentAccess().getCoursesCourseParserRuleCall_6_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_courses_6_0=ruleCourse();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDepartmentRule());
            	    					}
            	    					add(
            	    						current,
            	    						"courses",
            	    						lv_courses_6_0,
            	    						"tdt4250.ra.Rax2.Course");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            otherlv_7=(Token)match(input,14,FOLLOW_8); 

            			newLeafNode(otherlv_7, grammarAccess.getDepartmentAccess().getAllocationsKeyword_7());
            		
            // InternalRax2.g:169:3: ( (lv_resourceAllocations_8_0= ruleResourceAllocation ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=RULE_ID && LA3_0<=RULE_STRING)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalRax2.g:170:4: (lv_resourceAllocations_8_0= ruleResourceAllocation )
            	    {
            	    // InternalRax2.g:170:4: (lv_resourceAllocations_8_0= ruleResourceAllocation )
            	    // InternalRax2.g:171:5: lv_resourceAllocations_8_0= ruleResourceAllocation
            	    {

            	    					newCompositeNode(grammarAccess.getDepartmentAccess().getResourceAllocationsResourceAllocationParserRuleCall_8_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_resourceAllocations_8_0=ruleResourceAllocation();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDepartmentRule());
            	    					}
            	    					add(
            	    						current,
            	    						"resourceAllocations",
            	    						lv_resourceAllocations_8_0,
            	    						"tdt4250.ra.Rax2.ResourceAllocation");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDepartment"


    // $ANTLR start "entryRuleNameOrString"
    // InternalRax2.g:192:1: entryRuleNameOrString returns [String current=null] : iv_ruleNameOrString= ruleNameOrString EOF ;
    public final String entryRuleNameOrString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNameOrString = null;


        try {
            // InternalRax2.g:192:52: (iv_ruleNameOrString= ruleNameOrString EOF )
            // InternalRax2.g:193:2: iv_ruleNameOrString= ruleNameOrString EOF
            {
             newCompositeNode(grammarAccess.getNameOrStringRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNameOrString=ruleNameOrString();

            state._fsp--;

             current =iv_ruleNameOrString.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNameOrString"


    // $ANTLR start "ruleNameOrString"
    // InternalRax2.g:199:1: ruleNameOrString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleNameOrString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_STRING_1=null;


        	enterRule();

        try {
            // InternalRax2.g:205:2: ( (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING ) )
            // InternalRax2.g:206:2: (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING )
            {
            // InternalRax2.g:206:2: (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            else if ( (LA4_0==RULE_STRING) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalRax2.g:207:3: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    			current.merge(this_ID_0);
                    		

                    			newLeafNode(this_ID_0, grammarAccess.getNameOrStringAccess().getIDTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRax2.g:215:3: this_STRING_1= RULE_STRING
                    {
                    this_STRING_1=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    			current.merge(this_STRING_1);
                    		

                    			newLeafNode(this_STRING_1, grammarAccess.getNameOrStringAccess().getSTRINGTerminalRuleCall_1());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNameOrString"


    // $ANTLR start "entryRulePerson"
    // InternalRax2.g:226:1: entryRulePerson returns [EObject current=null] : iv_rulePerson= rulePerson EOF ;
    public final EObject entryRulePerson() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePerson = null;


        try {
            // InternalRax2.g:226:47: (iv_rulePerson= rulePerson EOF )
            // InternalRax2.g:227:2: iv_rulePerson= rulePerson EOF
            {
             newCompositeNode(grammarAccess.getPersonRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePerson=rulePerson();

            state._fsp--;

             current =iv_rulePerson; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePerson"


    // $ANTLR start "rulePerson"
    // InternalRax2.g:233:1: rulePerson returns [EObject current=null] : ( (lv_name_0_0= ruleNameOrString ) ) ;
    public final EObject rulePerson() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalRax2.g:239:2: ( ( (lv_name_0_0= ruleNameOrString ) ) )
            // InternalRax2.g:240:2: ( (lv_name_0_0= ruleNameOrString ) )
            {
            // InternalRax2.g:240:2: ( (lv_name_0_0= ruleNameOrString ) )
            // InternalRax2.g:241:3: (lv_name_0_0= ruleNameOrString )
            {
            // InternalRax2.g:241:3: (lv_name_0_0= ruleNameOrString )
            // InternalRax2.g:242:4: lv_name_0_0= ruleNameOrString
            {

            				newCompositeNode(grammarAccess.getPersonAccess().getNameNameOrStringParserRuleCall_0());
            			
            pushFollow(FOLLOW_2);
            lv_name_0_0=ruleNameOrString();

            state._fsp--;


            				if (current==null) {
            					current = createModelElementForParent(grammarAccess.getPersonRule());
            				}
            				set(
            					current,
            					"name",
            					lv_name_0_0,
            					"tdt4250.ra.Rax2.NameOrString");
            				afterParserOrEnumRuleCall();
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePerson"


    // $ANTLR start "entryRuleCourse"
    // InternalRax2.g:262:1: entryRuleCourse returns [EObject current=null] : iv_ruleCourse= ruleCourse EOF ;
    public final EObject entryRuleCourse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCourse = null;


        try {
            // InternalRax2.g:262:47: (iv_ruleCourse= ruleCourse EOF )
            // InternalRax2.g:263:2: iv_ruleCourse= ruleCourse EOF
            {
             newCompositeNode(grammarAccess.getCourseRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCourse=ruleCourse();

            state._fsp--;

             current =iv_ruleCourse; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCourse"


    // $ANTLR start "ruleCourse"
    // InternalRax2.g:269:1: ruleCourse returns [EObject current=null] : ( ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) (otherlv_3= 'requires' ( (lv_roles_4_0= ruleRole ) ) (otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) ) )* )? ) ;
    public final EObject ruleCourse() throws RecognitionException {
        EObject current = null;

        Token lv_code_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_roles_4_0 = null;

        EObject lv_roles_6_0 = null;



        	enterRule();

        try {
            // InternalRax2.g:275:2: ( ( ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) (otherlv_3= 'requires' ( (lv_roles_4_0= ruleRole ) ) (otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) ) )* )? ) )
            // InternalRax2.g:276:2: ( ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) (otherlv_3= 'requires' ( (lv_roles_4_0= ruleRole ) ) (otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) ) )* )? )
            {
            // InternalRax2.g:276:2: ( ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) (otherlv_3= 'requires' ( (lv_roles_4_0= ruleRole ) ) (otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) ) )* )? )
            // InternalRax2.g:277:3: ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) (otherlv_3= 'requires' ( (lv_roles_4_0= ruleRole ) ) (otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) ) )* )?
            {
            // InternalRax2.g:277:3: ( (lv_code_0_0= RULE_ID ) )
            // InternalRax2.g:278:4: (lv_code_0_0= RULE_ID )
            {
            // InternalRax2.g:278:4: (lv_code_0_0= RULE_ID )
            // InternalRax2.g:279:5: lv_code_0_0= RULE_ID
            {
            lv_code_0_0=(Token)match(input,RULE_ID,FOLLOW_3); 

            					newLeafNode(lv_code_0_0, grammarAccess.getCourseAccess().getCodeIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCourseRule());
            					}
            					setWithLastConsumed(
            						current,
            						"code",
            						lv_code_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,11,FOLLOW_9); 

            			newLeafNode(otherlv_1, grammarAccess.getCourseAccess().getHyphenMinusKeyword_1());
            		
            // InternalRax2.g:299:3: ( (lv_name_2_0= ruleNameOrString ) )
            // InternalRax2.g:300:4: (lv_name_2_0= ruleNameOrString )
            {
            // InternalRax2.g:300:4: (lv_name_2_0= ruleNameOrString )
            // InternalRax2.g:301:5: lv_name_2_0= ruleNameOrString
            {

            					newCompositeNode(grammarAccess.getCourseAccess().getNameNameOrStringParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_10);
            lv_name_2_0=ruleNameOrString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCourseRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"tdt4250.ra.Rax2.NameOrString");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRax2.g:318:3: (otherlv_3= 'requires' ( (lv_roles_4_0= ruleRole ) ) (otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) ) )* )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalRax2.g:319:4: otherlv_3= 'requires' ( (lv_roles_4_0= ruleRole ) ) (otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) ) )*
                    {
                    otherlv_3=(Token)match(input,15,FOLLOW_11); 

                    				newLeafNode(otherlv_3, grammarAccess.getCourseAccess().getRequiresKeyword_3_0());
                    			
                    // InternalRax2.g:323:4: ( (lv_roles_4_0= ruleRole ) )
                    // InternalRax2.g:324:5: (lv_roles_4_0= ruleRole )
                    {
                    // InternalRax2.g:324:5: (lv_roles_4_0= ruleRole )
                    // InternalRax2.g:325:6: lv_roles_4_0= ruleRole
                    {

                    						newCompositeNode(grammarAccess.getCourseAccess().getRolesRoleParserRuleCall_3_1_0());
                    					
                    pushFollow(FOLLOW_12);
                    lv_roles_4_0=ruleRole();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getCourseRule());
                    						}
                    						add(
                    							current,
                    							"roles",
                    							lv_roles_4_0,
                    							"tdt4250.ra.Rax2.Role");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRax2.g:342:4: (otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) ) )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==16) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalRax2.g:343:5: otherlv_5= ',' ( (lv_roles_6_0= ruleRole ) )
                    	    {
                    	    otherlv_5=(Token)match(input,16,FOLLOW_11); 

                    	    					newLeafNode(otherlv_5, grammarAccess.getCourseAccess().getCommaKeyword_3_2_0());
                    	    				
                    	    // InternalRax2.g:347:5: ( (lv_roles_6_0= ruleRole ) )
                    	    // InternalRax2.g:348:6: (lv_roles_6_0= ruleRole )
                    	    {
                    	    // InternalRax2.g:348:6: (lv_roles_6_0= ruleRole )
                    	    // InternalRax2.g:349:7: lv_roles_6_0= ruleRole
                    	    {

                    	    							newCompositeNode(grammarAccess.getCourseAccess().getRolesRoleParserRuleCall_3_2_1_0());
                    	    						
                    	    pushFollow(FOLLOW_12);
                    	    lv_roles_6_0=ruleRole();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getCourseRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"roles",
                    	    								lv_roles_6_0,
                    	    								"tdt4250.ra.Rax2.Role");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCourse"


    // $ANTLR start "entryRuleRole"
    // InternalRax2.g:372:1: entryRuleRole returns [EObject current=null] : iv_ruleRole= ruleRole EOF ;
    public final EObject entryRuleRole() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRole = null;


        try {
            // InternalRax2.g:372:45: (iv_ruleRole= ruleRole EOF )
            // InternalRax2.g:373:2: iv_ruleRole= ruleRole EOF
            {
             newCompositeNode(grammarAccess.getRoleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRole=ruleRole();

            state._fsp--;

             current =iv_ruleRole; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRole"


    // $ANTLR start "ruleRole"
    // InternalRax2.g:379:1: ruleRole returns [EObject current=null] : ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'for' ( (lv_workload_2_0= ruleFactorAsPercentage ) ) ) ;
    public final EObject ruleRole() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;
        Token otherlv_1=null;
        AntlrDatatypeRuleToken lv_workload_2_0 = null;



        	enterRule();

        try {
            // InternalRax2.g:385:2: ( ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'for' ( (lv_workload_2_0= ruleFactorAsPercentage ) ) ) )
            // InternalRax2.g:386:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'for' ( (lv_workload_2_0= ruleFactorAsPercentage ) ) )
            {
            // InternalRax2.g:386:2: ( ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'for' ( (lv_workload_2_0= ruleFactorAsPercentage ) ) )
            // InternalRax2.g:387:3: ( (lv_name_0_0= RULE_ID ) ) otherlv_1= 'for' ( (lv_workload_2_0= ruleFactorAsPercentage ) )
            {
            // InternalRax2.g:387:3: ( (lv_name_0_0= RULE_ID ) )
            // InternalRax2.g:388:4: (lv_name_0_0= RULE_ID )
            {
            // InternalRax2.g:388:4: (lv_name_0_0= RULE_ID )
            // InternalRax2.g:389:5: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_13); 

            					newLeafNode(lv_name_0_0, grammarAccess.getRoleAccess().getNameIDTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRoleRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_0_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_1=(Token)match(input,17,FOLLOW_14); 

            			newLeafNode(otherlv_1, grammarAccess.getRoleAccess().getForKeyword_1());
            		
            // InternalRax2.g:409:3: ( (lv_workload_2_0= ruleFactorAsPercentage ) )
            // InternalRax2.g:410:4: (lv_workload_2_0= ruleFactorAsPercentage )
            {
            // InternalRax2.g:410:4: (lv_workload_2_0= ruleFactorAsPercentage )
            // InternalRax2.g:411:5: lv_workload_2_0= ruleFactorAsPercentage
            {

            					newCompositeNode(grammarAccess.getRoleAccess().getWorkloadFactorAsPercentageParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_workload_2_0=ruleFactorAsPercentage();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getRoleRule());
            					}
            					set(
            						current,
            						"workload",
            						lv_workload_2_0,
            						"tdt4250.ra.Rax2.FactorAsPercentage");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRole"


    // $ANTLR start "entryRuleFactorAsPercentage"
    // InternalRax2.g:432:1: entryRuleFactorAsPercentage returns [String current=null] : iv_ruleFactorAsPercentage= ruleFactorAsPercentage EOF ;
    public final String entryRuleFactorAsPercentage() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleFactorAsPercentage = null;


        try {
            // InternalRax2.g:432:58: (iv_ruleFactorAsPercentage= ruleFactorAsPercentage EOF )
            // InternalRax2.g:433:2: iv_ruleFactorAsPercentage= ruleFactorAsPercentage EOF
            {
             newCompositeNode(grammarAccess.getFactorAsPercentageRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFactorAsPercentage=ruleFactorAsPercentage();

            state._fsp--;

             current =iv_ruleFactorAsPercentage.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFactorAsPercentage"


    // $ANTLR start "ruleFactorAsPercentage"
    // InternalRax2.g:439:1: ruleFactorAsPercentage returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? kw= '%' ) ;
    public final AntlrDatatypeRuleToken ruleFactorAsPercentage() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;
        Token kw=null;
        Token this_INT_2=null;


        	enterRule();

        try {
            // InternalRax2.g:445:2: ( (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? kw= '%' ) )
            // InternalRax2.g:446:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? kw= '%' )
            {
            // InternalRax2.g:446:2: (this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? kw= '%' )
            // InternalRax2.g:447:3: this_INT_0= RULE_INT (kw= '.' this_INT_2= RULE_INT )? kw= '%'
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_15); 

            			current.merge(this_INT_0);
            		

            			newLeafNode(this_INT_0, grammarAccess.getFactorAsPercentageAccess().getINTTerminalRuleCall_0());
            		
            // InternalRax2.g:454:3: (kw= '.' this_INT_2= RULE_INT )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==18) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // InternalRax2.g:455:4: kw= '.' this_INT_2= RULE_INT
                    {
                    kw=(Token)match(input,18,FOLLOW_14); 

                    				current.merge(kw);
                    				newLeafNode(kw, grammarAccess.getFactorAsPercentageAccess().getFullStopKeyword_1_0());
                    			
                    this_INT_2=(Token)match(input,RULE_INT,FOLLOW_16); 

                    				current.merge(this_INT_2);
                    			

                    				newLeafNode(this_INT_2, grammarAccess.getFactorAsPercentageAccess().getINTTerminalRuleCall_1_1());
                    			

                    }
                    break;

            }

            kw=(Token)match(input,19,FOLLOW_2); 

            			current.merge(kw);
            			newLeafNode(kw, grammarAccess.getFactorAsPercentageAccess().getPercentSignKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFactorAsPercentage"


    // $ANTLR start "entryRuleResourceAllocation"
    // InternalRax2.g:477:1: entryRuleResourceAllocation returns [EObject current=null] : iv_ruleResourceAllocation= ruleResourceAllocation EOF ;
    public final EObject entryRuleResourceAllocation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceAllocation = null;


        try {
            // InternalRax2.g:477:59: (iv_ruleResourceAllocation= ruleResourceAllocation EOF )
            // InternalRax2.g:478:2: iv_ruleResourceAllocation= ruleResourceAllocation EOF
            {
             newCompositeNode(grammarAccess.getResourceAllocationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleResourceAllocation=ruleResourceAllocation();

            state._fsp--;

             current =iv_ruleResourceAllocation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleResourceAllocation"


    // $ANTLR start "ruleResourceAllocation"
    // InternalRax2.g:484:1: ruleResourceAllocation returns [EObject current=null] : ( ( ( ruleNameOrString ) ) otherlv_1= 'is' ( ( ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) ) ) | (otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) ) ) ) ) ;
    public final EObject ruleResourceAllocation() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_9=null;
        AntlrDatatypeRuleToken lv_factor_2_0 = null;

        AntlrDatatypeRuleToken lv_factor_8_0 = null;



        	enterRule();

        try {
            // InternalRax2.g:490:2: ( ( ( ( ruleNameOrString ) ) otherlv_1= 'is' ( ( ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) ) ) | (otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) ) ) ) ) )
            // InternalRax2.g:491:2: ( ( ( ruleNameOrString ) ) otherlv_1= 'is' ( ( ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) ) ) | (otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) ) ) ) )
            {
            // InternalRax2.g:491:2: ( ( ( ruleNameOrString ) ) otherlv_1= 'is' ( ( ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) ) ) | (otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) ) ) ) )
            // InternalRax2.g:492:3: ( ( ruleNameOrString ) ) otherlv_1= 'is' ( ( ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) ) ) | (otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) ) ) )
            {
            // InternalRax2.g:492:3: ( ( ruleNameOrString ) )
            // InternalRax2.g:493:4: ( ruleNameOrString )
            {
            // InternalRax2.g:493:4: ( ruleNameOrString )
            // InternalRax2.g:494:5: ruleNameOrString
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceAllocationRule());
            					}
            				

            					newCompositeNode(grammarAccess.getResourceAllocationAccess().getPersonPersonCrossReference_0_0());
            				
            pushFollow(FOLLOW_17);
            ruleNameOrString();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_1=(Token)match(input,20,FOLLOW_18); 

            			newLeafNode(otherlv_1, grammarAccess.getResourceAllocationAccess().getIsKeyword_1());
            		
            // InternalRax2.g:512:3: ( ( ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) ) ) | (otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) ) ) )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_INT) ) {
                alt8=1;
            }
            else if ( (LA8_0==21) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalRax2.g:513:4: ( ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) ) )
                    {
                    // InternalRax2.g:513:4: ( ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) ) )
                    // InternalRax2.g:514:5: ( (lv_factor_2_0= ruleFactorAsPercentage ) ) otherlv_3= 'in' ( (otherlv_4= RULE_ID ) )
                    {
                    // InternalRax2.g:514:5: ( (lv_factor_2_0= ruleFactorAsPercentage ) )
                    // InternalRax2.g:515:6: (lv_factor_2_0= ruleFactorAsPercentage )
                    {
                    // InternalRax2.g:515:6: (lv_factor_2_0= ruleFactorAsPercentage )
                    // InternalRax2.g:516:7: lv_factor_2_0= ruleFactorAsPercentage
                    {

                    							newCompositeNode(grammarAccess.getResourceAllocationAccess().getFactorFactorAsPercentageParserRuleCall_2_0_0_0());
                    						
                    pushFollow(FOLLOW_19);
                    lv_factor_2_0=ruleFactorAsPercentage();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getResourceAllocationRule());
                    							}
                    							set(
                    								current,
                    								"factor",
                    								lv_factor_2_0,
                    								"tdt4250.ra.Rax2.FactorAsPercentage");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    otherlv_3=(Token)match(input,21,FOLLOW_11); 

                    					newLeafNode(otherlv_3, grammarAccess.getResourceAllocationAccess().getInKeyword_2_0_1());
                    				
                    // InternalRax2.g:537:5: ( (otherlv_4= RULE_ID ) )
                    // InternalRax2.g:538:6: (otherlv_4= RULE_ID )
                    {
                    // InternalRax2.g:538:6: (otherlv_4= RULE_ID )
                    // InternalRax2.g:539:7: otherlv_4= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getResourceAllocationRule());
                    							}
                    						
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_2); 

                    							newLeafNode(otherlv_4, grammarAccess.getResourceAllocationAccess().getCourseCourseCrossReference_2_0_2_0());
                    						

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalRax2.g:552:4: (otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) ) )
                    {
                    // InternalRax2.g:552:4: (otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) ) )
                    // InternalRax2.g:553:5: otherlv_5= 'in' ( (otherlv_6= RULE_ID ) ) otherlv_7= 'as' ( (lv_factor_8_0= ruleFactorAsPercentage ) ) ( (otherlv_9= RULE_ID ) )
                    {
                    otherlv_5=(Token)match(input,21,FOLLOW_11); 

                    					newLeafNode(otherlv_5, grammarAccess.getResourceAllocationAccess().getInKeyword_2_1_0());
                    				
                    // InternalRax2.g:557:5: ( (otherlv_6= RULE_ID ) )
                    // InternalRax2.g:558:6: (otherlv_6= RULE_ID )
                    {
                    // InternalRax2.g:558:6: (otherlv_6= RULE_ID )
                    // InternalRax2.g:559:7: otherlv_6= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getResourceAllocationRule());
                    							}
                    						
                    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_20); 

                    							newLeafNode(otherlv_6, grammarAccess.getResourceAllocationAccess().getCourseCourseCrossReference_2_1_1_0());
                    						

                    }


                    }

                    otherlv_7=(Token)match(input,22,FOLLOW_14); 

                    					newLeafNode(otherlv_7, grammarAccess.getResourceAllocationAccess().getAsKeyword_2_1_2());
                    				
                    // InternalRax2.g:574:5: ( (lv_factor_8_0= ruleFactorAsPercentage ) )
                    // InternalRax2.g:575:6: (lv_factor_8_0= ruleFactorAsPercentage )
                    {
                    // InternalRax2.g:575:6: (lv_factor_8_0= ruleFactorAsPercentage )
                    // InternalRax2.g:576:7: lv_factor_8_0= ruleFactorAsPercentage
                    {

                    							newCompositeNode(grammarAccess.getResourceAllocationAccess().getFactorFactorAsPercentageParserRuleCall_2_1_3_0());
                    						
                    pushFollow(FOLLOW_11);
                    lv_factor_8_0=ruleFactorAsPercentage();

                    state._fsp--;


                    							if (current==null) {
                    								current = createModelElementForParent(grammarAccess.getResourceAllocationRule());
                    							}
                    							set(
                    								current,
                    								"factor",
                    								lv_factor_8_0,
                    								"tdt4250.ra.Rax2.FactorAsPercentage");
                    							afterParserOrEnumRuleCall();
                    						

                    }


                    }

                    // InternalRax2.g:593:5: ( (otherlv_9= RULE_ID ) )
                    // InternalRax2.g:594:6: (otherlv_9= RULE_ID )
                    {
                    // InternalRax2.g:594:6: (otherlv_9= RULE_ID )
                    // InternalRax2.g:595:7: otherlv_9= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getResourceAllocationRule());
                    							}
                    						
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_2); 

                    							newLeafNode(otherlv_9, grammarAccess.getResourceAllocationAccess().getRoleRoleCrossReference_2_1_4_0());
                    						

                    }


                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleResourceAllocation"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000200040L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000400000L});

}