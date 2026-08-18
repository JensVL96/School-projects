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
import tdt4250.ra.services.Rax1GrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalRax1Parser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'-'", "'Persons:'", "'Courses:'", "':'", "','", "'%'"
    };
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_STRING=5;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int RULE_INT=6;
    public static final int T__11=11;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;

    // delegates
    // delegators


        public InternalRax1Parser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalRax1Parser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalRax1Parser.tokenNames; }
    public String getGrammarFileName() { return "InternalRax1.g"; }



     	private Rax1GrammarAccess grammarAccess;

        public InternalRax1Parser(TokenStream input, Rax1GrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Department";
       	}

       	@Override
       	protected Rax1GrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleDepartment"
    // InternalRax1.g:64:1: entryRuleDepartment returns [EObject current=null] : iv_ruleDepartment= ruleDepartment EOF ;
    public final EObject entryRuleDepartment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDepartment = null;


        try {
            // InternalRax1.g:64:51: (iv_ruleDepartment= ruleDepartment EOF )
            // InternalRax1.g:65:2: iv_ruleDepartment= ruleDepartment EOF
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
    // InternalRax1.g:71:1: ruleDepartment returns [EObject current=null] : ( ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_persons_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* ) ;
    public final EObject ruleDepartment() throws RecognitionException {
        EObject current = null;

        Token lv_shortName_0_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_persons_4_0 = null;

        EObject lv_courses_6_0 = null;



        	enterRule();

        try {
            // InternalRax1.g:77:2: ( ( ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_persons_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* ) )
            // InternalRax1.g:78:2: ( ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_persons_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* )
            {
            // InternalRax1.g:78:2: ( ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_persons_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )* )
            // InternalRax1.g:79:3: ( (lv_shortName_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= RULE_STRING ) ) otherlv_3= 'Persons:' ( (lv_persons_4_0= rulePerson ) )* otherlv_5= 'Courses:' ( (lv_courses_6_0= ruleCourse ) )*
            {
            // InternalRax1.g:79:3: ( (lv_shortName_0_0= RULE_ID ) )
            // InternalRax1.g:80:4: (lv_shortName_0_0= RULE_ID )
            {
            // InternalRax1.g:80:4: (lv_shortName_0_0= RULE_ID )
            // InternalRax1.g:81:5: lv_shortName_0_0= RULE_ID
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
            		
            // InternalRax1.g:101:3: ( (lv_name_2_0= RULE_STRING ) )
            // InternalRax1.g:102:4: (lv_name_2_0= RULE_STRING )
            {
            // InternalRax1.g:102:4: (lv_name_2_0= RULE_STRING )
            // InternalRax1.g:103:5: lv_name_2_0= RULE_STRING
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
            		
            // InternalRax1.g:123:3: ( (lv_persons_4_0= rulePerson ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_ID && LA1_0<=RULE_STRING)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalRax1.g:124:4: (lv_persons_4_0= rulePerson )
            	    {
            	    // InternalRax1.g:124:4: (lv_persons_4_0= rulePerson )
            	    // InternalRax1.g:125:5: lv_persons_4_0= rulePerson
            	    {

            	    					newCompositeNode(grammarAccess.getDepartmentAccess().getPersonsPersonParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_persons_4_0=rulePerson();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getDepartmentRule());
            	    					}
            	    					add(
            	    						current,
            	    						"persons",
            	    						lv_persons_4_0,
            	    						"tdt4250.ra.Rax1.Person");
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
            		
            // InternalRax1.g:146:3: ( (lv_courses_6_0= ruleCourse ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_ID) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalRax1.g:147:4: (lv_courses_6_0= ruleCourse )
            	    {
            	    // InternalRax1.g:147:4: (lv_courses_6_0= ruleCourse )
            	    // InternalRax1.g:148:5: lv_courses_6_0= ruleCourse
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
            	    						"tdt4250.ra.Rax1.Course");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
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
    // InternalRax1.g:169:1: entryRuleNameOrString returns [String current=null] : iv_ruleNameOrString= ruleNameOrString EOF ;
    public final String entryRuleNameOrString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNameOrString = null;


        try {
            // InternalRax1.g:169:52: (iv_ruleNameOrString= ruleNameOrString EOF )
            // InternalRax1.g:170:2: iv_ruleNameOrString= ruleNameOrString EOF
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
    // InternalRax1.g:176:1: ruleNameOrString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING ) ;
    public final AntlrDatatypeRuleToken ruleNameOrString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_ID_0=null;
        Token this_STRING_1=null;


        	enterRule();

        try {
            // InternalRax1.g:182:2: ( (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING ) )
            // InternalRax1.g:183:2: (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING )
            {
            // InternalRax1.g:183:2: (this_ID_0= RULE_ID | this_STRING_1= RULE_STRING )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==RULE_ID) ) {
                alt3=1;
            }
            else if ( (LA3_0==RULE_STRING) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalRax1.g:184:3: this_ID_0= RULE_ID
                    {
                    this_ID_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    			current.merge(this_ID_0);
                    		

                    			newLeafNode(this_ID_0, grammarAccess.getNameOrStringAccess().getIDTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalRax1.g:192:3: this_STRING_1= RULE_STRING
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
    // InternalRax1.g:203:1: entryRulePerson returns [EObject current=null] : iv_rulePerson= rulePerson EOF ;
    public final EObject entryRulePerson() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePerson = null;


        try {
            // InternalRax1.g:203:47: (iv_rulePerson= rulePerson EOF )
            // InternalRax1.g:204:2: iv_rulePerson= rulePerson EOF
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
    // InternalRax1.g:210:1: rulePerson returns [EObject current=null] : ( (lv_name_0_0= ruleNameOrString ) ) ;
    public final EObject rulePerson() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;



        	enterRule();

        try {
            // InternalRax1.g:216:2: ( ( (lv_name_0_0= ruleNameOrString ) ) )
            // InternalRax1.g:217:2: ( (lv_name_0_0= ruleNameOrString ) )
            {
            // InternalRax1.g:217:2: ( (lv_name_0_0= ruleNameOrString ) )
            // InternalRax1.g:218:3: (lv_name_0_0= ruleNameOrString )
            {
            // InternalRax1.g:218:3: (lv_name_0_0= ruleNameOrString )
            // InternalRax1.g:219:4: lv_name_0_0= ruleNameOrString
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
            					"tdt4250.ra.Rax1.NameOrString");
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
    // InternalRax1.g:239:1: entryRuleCourse returns [EObject current=null] : iv_ruleCourse= ruleCourse EOF ;
    public final EObject entryRuleCourse() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCourse = null;


        try {
            // InternalRax1.g:239:47: (iv_ruleCourse= ruleCourse EOF )
            // InternalRax1.g:240:2: iv_ruleCourse= ruleCourse EOF
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
    // InternalRax1.g:246:1: ruleCourse returns [EObject current=null] : ( ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) otherlv_3= ':' ( ( (lv_allocations_4_0= ruleResourceAllocation ) ) (otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) ) )* )? ) ;
    public final EObject ruleCourse() throws RecognitionException {
        EObject current = null;

        Token lv_code_0_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        EObject lv_allocations_4_0 = null;

        EObject lv_allocations_6_0 = null;



        	enterRule();

        try {
            // InternalRax1.g:252:2: ( ( ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) otherlv_3= ':' ( ( (lv_allocations_4_0= ruleResourceAllocation ) ) (otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) ) )* )? ) )
            // InternalRax1.g:253:2: ( ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) otherlv_3= ':' ( ( (lv_allocations_4_0= ruleResourceAllocation ) ) (otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) ) )* )? )
            {
            // InternalRax1.g:253:2: ( ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) otherlv_3= ':' ( ( (lv_allocations_4_0= ruleResourceAllocation ) ) (otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) ) )* )? )
            // InternalRax1.g:254:3: ( (lv_code_0_0= RULE_ID ) ) otherlv_1= '-' ( (lv_name_2_0= ruleNameOrString ) ) otherlv_3= ':' ( ( (lv_allocations_4_0= ruleResourceAllocation ) ) (otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) ) )* )?
            {
            // InternalRax1.g:254:3: ( (lv_code_0_0= RULE_ID ) )
            // InternalRax1.g:255:4: (lv_code_0_0= RULE_ID )
            {
            // InternalRax1.g:255:4: (lv_code_0_0= RULE_ID )
            // InternalRax1.g:256:5: lv_code_0_0= RULE_ID
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

            otherlv_1=(Token)match(input,11,FOLLOW_8); 

            			newLeafNode(otherlv_1, grammarAccess.getCourseAccess().getHyphenMinusKeyword_1());
            		
            // InternalRax1.g:276:3: ( (lv_name_2_0= ruleNameOrString ) )
            // InternalRax1.g:277:4: (lv_name_2_0= ruleNameOrString )
            {
            // InternalRax1.g:277:4: (lv_name_2_0= ruleNameOrString )
            // InternalRax1.g:278:5: lv_name_2_0= ruleNameOrString
            {

            					newCompositeNode(grammarAccess.getCourseAccess().getNameNameOrStringParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_9);
            lv_name_2_0=ruleNameOrString();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getCourseRule());
            					}
            					set(
            						current,
            						"name",
            						lv_name_2_0,
            						"tdt4250.ra.Rax1.NameOrString");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,14,FOLLOW_10); 

            			newLeafNode(otherlv_3, grammarAccess.getCourseAccess().getColonKeyword_3());
            		
            // InternalRax1.g:299:3: ( ( (lv_allocations_4_0= ruleResourceAllocation ) ) (otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) ) )* )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==RULE_INT) ) {
                    alt5=1;
                }
            }
            else if ( (LA5_0==RULE_STRING) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalRax1.g:300:4: ( (lv_allocations_4_0= ruleResourceAllocation ) ) (otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) ) )*
                    {
                    // InternalRax1.g:300:4: ( (lv_allocations_4_0= ruleResourceAllocation ) )
                    // InternalRax1.g:301:5: (lv_allocations_4_0= ruleResourceAllocation )
                    {
                    // InternalRax1.g:301:5: (lv_allocations_4_0= ruleResourceAllocation )
                    // InternalRax1.g:302:6: lv_allocations_4_0= ruleResourceAllocation
                    {

                    						newCompositeNode(grammarAccess.getCourseAccess().getAllocationsResourceAllocationParserRuleCall_4_0_0());
                    					
                    pushFollow(FOLLOW_11);
                    lv_allocations_4_0=ruleResourceAllocation();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getCourseRule());
                    						}
                    						add(
                    							current,
                    							"allocations",
                    							lv_allocations_4_0,
                    							"tdt4250.ra.Rax1.ResourceAllocation");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalRax1.g:319:4: (otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) ) )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==15) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // InternalRax1.g:320:5: otherlv_5= ',' ( (lv_allocations_6_0= ruleResourceAllocation ) )
                    	    {
                    	    otherlv_5=(Token)match(input,15,FOLLOW_8); 

                    	    					newLeafNode(otherlv_5, grammarAccess.getCourseAccess().getCommaKeyword_4_1_0());
                    	    				
                    	    // InternalRax1.g:324:5: ( (lv_allocations_6_0= ruleResourceAllocation ) )
                    	    // InternalRax1.g:325:6: (lv_allocations_6_0= ruleResourceAllocation )
                    	    {
                    	    // InternalRax1.g:325:6: (lv_allocations_6_0= ruleResourceAllocation )
                    	    // InternalRax1.g:326:7: lv_allocations_6_0= ruleResourceAllocation
                    	    {

                    	    							newCompositeNode(grammarAccess.getCourseAccess().getAllocationsResourceAllocationParserRuleCall_4_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_11);
                    	    lv_allocations_6_0=ruleResourceAllocation();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getCourseRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"allocations",
                    	    								lv_allocations_6_0,
                    	    								"tdt4250.ra.Rax1.ResourceAllocation");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
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


    // $ANTLR start "entryRuleResourceAllocation"
    // InternalRax1.g:349:1: entryRuleResourceAllocation returns [EObject current=null] : iv_ruleResourceAllocation= ruleResourceAllocation EOF ;
    public final EObject entryRuleResourceAllocation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleResourceAllocation = null;


        try {
            // InternalRax1.g:349:59: (iv_ruleResourceAllocation= ruleResourceAllocation EOF )
            // InternalRax1.g:350:2: iv_ruleResourceAllocation= ruleResourceAllocation EOF
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
    // InternalRax1.g:356:1: ruleResourceAllocation returns [EObject current=null] : ( ( ( ruleNameOrString ) ) ( (lv_percentage_1_0= RULE_INT ) ) otherlv_2= '%' ) ;
    public final EObject ruleResourceAllocation() throws RecognitionException {
        EObject current = null;

        Token lv_percentage_1_0=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalRax1.g:362:2: ( ( ( ( ruleNameOrString ) ) ( (lv_percentage_1_0= RULE_INT ) ) otherlv_2= '%' ) )
            // InternalRax1.g:363:2: ( ( ( ruleNameOrString ) ) ( (lv_percentage_1_0= RULE_INT ) ) otherlv_2= '%' )
            {
            // InternalRax1.g:363:2: ( ( ( ruleNameOrString ) ) ( (lv_percentage_1_0= RULE_INT ) ) otherlv_2= '%' )
            // InternalRax1.g:364:3: ( ( ruleNameOrString ) ) ( (lv_percentage_1_0= RULE_INT ) ) otherlv_2= '%'
            {
            // InternalRax1.g:364:3: ( ( ruleNameOrString ) )
            // InternalRax1.g:365:4: ( ruleNameOrString )
            {
            // InternalRax1.g:365:4: ( ruleNameOrString )
            // InternalRax1.g:366:5: ruleNameOrString
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceAllocationRule());
            					}
            				

            					newCompositeNode(grammarAccess.getResourceAllocationAccess().getPersonPersonCrossReference_0_0());
            				
            pushFollow(FOLLOW_12);
            ruleNameOrString();

            state._fsp--;


            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalRax1.g:380:3: ( (lv_percentage_1_0= RULE_INT ) )
            // InternalRax1.g:381:4: (lv_percentage_1_0= RULE_INT )
            {
            // InternalRax1.g:381:4: (lv_percentage_1_0= RULE_INT )
            // InternalRax1.g:382:5: lv_percentage_1_0= RULE_INT
            {
            lv_percentage_1_0=(Token)match(input,RULE_INT,FOLLOW_13); 

            					newLeafNode(lv_percentage_1_0, grammarAccess.getResourceAllocationAccess().getPercentageINTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getResourceAllocationRule());
            					}
            					setWithLastConsumed(
            						current,
            						"percentage",
            						lv_percentage_1_0,
            						"org.eclipse.xtext.common.Terminals.INT");
            				

            }


            }

            otherlv_2=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getResourceAllocationAccess().getPercentSignKeyword_2());
            		

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
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000010000L});

}