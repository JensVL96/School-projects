package tdt4250.ra.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import tdt4250.ra.services.Rax1GrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalRax1Parser extends AbstractInternalContentAssistParser {
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

    	public void setGrammarAccess(Rax1GrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleDepartment"
    // InternalRax1.g:53:1: entryRuleDepartment : ruleDepartment EOF ;
    public final void entryRuleDepartment() throws RecognitionException {
        try {
            // InternalRax1.g:54:1: ( ruleDepartment EOF )
            // InternalRax1.g:55:1: ruleDepartment EOF
            {
             before(grammarAccess.getDepartmentRule()); 
            pushFollow(FOLLOW_1);
            ruleDepartment();

            state._fsp--;

             after(grammarAccess.getDepartmentRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleDepartment"


    // $ANTLR start "ruleDepartment"
    // InternalRax1.g:62:1: ruleDepartment : ( ( rule__Department__Group__0 ) ) ;
    public final void ruleDepartment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:66:2: ( ( ( rule__Department__Group__0 ) ) )
            // InternalRax1.g:67:2: ( ( rule__Department__Group__0 ) )
            {
            // InternalRax1.g:67:2: ( ( rule__Department__Group__0 ) )
            // InternalRax1.g:68:3: ( rule__Department__Group__0 )
            {
             before(grammarAccess.getDepartmentAccess().getGroup()); 
            // InternalRax1.g:69:3: ( rule__Department__Group__0 )
            // InternalRax1.g:69:4: rule__Department__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Department__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getDepartmentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleDepartment"


    // $ANTLR start "entryRuleNameOrString"
    // InternalRax1.g:78:1: entryRuleNameOrString : ruleNameOrString EOF ;
    public final void entryRuleNameOrString() throws RecognitionException {
        try {
            // InternalRax1.g:79:1: ( ruleNameOrString EOF )
            // InternalRax1.g:80:1: ruleNameOrString EOF
            {
             before(grammarAccess.getNameOrStringRule()); 
            pushFollow(FOLLOW_1);
            ruleNameOrString();

            state._fsp--;

             after(grammarAccess.getNameOrStringRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNameOrString"


    // $ANTLR start "ruleNameOrString"
    // InternalRax1.g:87:1: ruleNameOrString : ( ( rule__NameOrString__Alternatives ) ) ;
    public final void ruleNameOrString() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:91:2: ( ( ( rule__NameOrString__Alternatives ) ) )
            // InternalRax1.g:92:2: ( ( rule__NameOrString__Alternatives ) )
            {
            // InternalRax1.g:92:2: ( ( rule__NameOrString__Alternatives ) )
            // InternalRax1.g:93:3: ( rule__NameOrString__Alternatives )
            {
             before(grammarAccess.getNameOrStringAccess().getAlternatives()); 
            // InternalRax1.g:94:3: ( rule__NameOrString__Alternatives )
            // InternalRax1.g:94:4: rule__NameOrString__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__NameOrString__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getNameOrStringAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNameOrString"


    // $ANTLR start "entryRulePerson"
    // InternalRax1.g:103:1: entryRulePerson : rulePerson EOF ;
    public final void entryRulePerson() throws RecognitionException {
        try {
            // InternalRax1.g:104:1: ( rulePerson EOF )
            // InternalRax1.g:105:1: rulePerson EOF
            {
             before(grammarAccess.getPersonRule()); 
            pushFollow(FOLLOW_1);
            rulePerson();

            state._fsp--;

             after(grammarAccess.getPersonRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePerson"


    // $ANTLR start "rulePerson"
    // InternalRax1.g:112:1: rulePerson : ( ( rule__Person__NameAssignment ) ) ;
    public final void rulePerson() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:116:2: ( ( ( rule__Person__NameAssignment ) ) )
            // InternalRax1.g:117:2: ( ( rule__Person__NameAssignment ) )
            {
            // InternalRax1.g:117:2: ( ( rule__Person__NameAssignment ) )
            // InternalRax1.g:118:3: ( rule__Person__NameAssignment )
            {
             before(grammarAccess.getPersonAccess().getNameAssignment()); 
            // InternalRax1.g:119:3: ( rule__Person__NameAssignment )
            // InternalRax1.g:119:4: rule__Person__NameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Person__NameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getPersonAccess().getNameAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePerson"


    // $ANTLR start "entryRuleCourse"
    // InternalRax1.g:128:1: entryRuleCourse : ruleCourse EOF ;
    public final void entryRuleCourse() throws RecognitionException {
        try {
            // InternalRax1.g:129:1: ( ruleCourse EOF )
            // InternalRax1.g:130:1: ruleCourse EOF
            {
             before(grammarAccess.getCourseRule()); 
            pushFollow(FOLLOW_1);
            ruleCourse();

            state._fsp--;

             after(grammarAccess.getCourseRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCourse"


    // $ANTLR start "ruleCourse"
    // InternalRax1.g:137:1: ruleCourse : ( ( rule__Course__Group__0 ) ) ;
    public final void ruleCourse() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:141:2: ( ( ( rule__Course__Group__0 ) ) )
            // InternalRax1.g:142:2: ( ( rule__Course__Group__0 ) )
            {
            // InternalRax1.g:142:2: ( ( rule__Course__Group__0 ) )
            // InternalRax1.g:143:3: ( rule__Course__Group__0 )
            {
             before(grammarAccess.getCourseAccess().getGroup()); 
            // InternalRax1.g:144:3: ( rule__Course__Group__0 )
            // InternalRax1.g:144:4: rule__Course__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Course__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCourseAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCourse"


    // $ANTLR start "entryRuleResourceAllocation"
    // InternalRax1.g:153:1: entryRuleResourceAllocation : ruleResourceAllocation EOF ;
    public final void entryRuleResourceAllocation() throws RecognitionException {
        try {
            // InternalRax1.g:154:1: ( ruleResourceAllocation EOF )
            // InternalRax1.g:155:1: ruleResourceAllocation EOF
            {
             before(grammarAccess.getResourceAllocationRule()); 
            pushFollow(FOLLOW_1);
            ruleResourceAllocation();

            state._fsp--;

             after(grammarAccess.getResourceAllocationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleResourceAllocation"


    // $ANTLR start "ruleResourceAllocation"
    // InternalRax1.g:162:1: ruleResourceAllocation : ( ( rule__ResourceAllocation__Group__0 ) ) ;
    public final void ruleResourceAllocation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:166:2: ( ( ( rule__ResourceAllocation__Group__0 ) ) )
            // InternalRax1.g:167:2: ( ( rule__ResourceAllocation__Group__0 ) )
            {
            // InternalRax1.g:167:2: ( ( rule__ResourceAllocation__Group__0 ) )
            // InternalRax1.g:168:3: ( rule__ResourceAllocation__Group__0 )
            {
             before(grammarAccess.getResourceAllocationAccess().getGroup()); 
            // InternalRax1.g:169:3: ( rule__ResourceAllocation__Group__0 )
            // InternalRax1.g:169:4: rule__ResourceAllocation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleResourceAllocation"


    // $ANTLR start "rule__NameOrString__Alternatives"
    // InternalRax1.g:177:1: rule__NameOrString__Alternatives : ( ( RULE_ID ) | ( RULE_STRING ) );
    public final void rule__NameOrString__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:181:1: ( ( RULE_ID ) | ( RULE_STRING ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==RULE_ID) ) {
                alt1=1;
            }
            else if ( (LA1_0==RULE_STRING) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalRax1.g:182:2: ( RULE_ID )
                    {
                    // InternalRax1.g:182:2: ( RULE_ID )
                    // InternalRax1.g:183:3: RULE_ID
                    {
                     before(grammarAccess.getNameOrStringAccess().getIDTerminalRuleCall_0()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getNameOrStringAccess().getIDTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalRax1.g:188:2: ( RULE_STRING )
                    {
                    // InternalRax1.g:188:2: ( RULE_STRING )
                    // InternalRax1.g:189:3: RULE_STRING
                    {
                     before(grammarAccess.getNameOrStringAccess().getSTRINGTerminalRuleCall_1()); 
                    match(input,RULE_STRING,FOLLOW_2); 
                     after(grammarAccess.getNameOrStringAccess().getSTRINGTerminalRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NameOrString__Alternatives"


    // $ANTLR start "rule__Department__Group__0"
    // InternalRax1.g:198:1: rule__Department__Group__0 : rule__Department__Group__0__Impl rule__Department__Group__1 ;
    public final void rule__Department__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:202:1: ( rule__Department__Group__0__Impl rule__Department__Group__1 )
            // InternalRax1.g:203:2: rule__Department__Group__0__Impl rule__Department__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Department__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Department__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__0"


    // $ANTLR start "rule__Department__Group__0__Impl"
    // InternalRax1.g:210:1: rule__Department__Group__0__Impl : ( ( rule__Department__ShortNameAssignment_0 ) ) ;
    public final void rule__Department__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:214:1: ( ( ( rule__Department__ShortNameAssignment_0 ) ) )
            // InternalRax1.g:215:1: ( ( rule__Department__ShortNameAssignment_0 ) )
            {
            // InternalRax1.g:215:1: ( ( rule__Department__ShortNameAssignment_0 ) )
            // InternalRax1.g:216:2: ( rule__Department__ShortNameAssignment_0 )
            {
             before(grammarAccess.getDepartmentAccess().getShortNameAssignment_0()); 
            // InternalRax1.g:217:2: ( rule__Department__ShortNameAssignment_0 )
            // InternalRax1.g:217:3: rule__Department__ShortNameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Department__ShortNameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getDepartmentAccess().getShortNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__0__Impl"


    // $ANTLR start "rule__Department__Group__1"
    // InternalRax1.g:225:1: rule__Department__Group__1 : rule__Department__Group__1__Impl rule__Department__Group__2 ;
    public final void rule__Department__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:229:1: ( rule__Department__Group__1__Impl rule__Department__Group__2 )
            // InternalRax1.g:230:2: rule__Department__Group__1__Impl rule__Department__Group__2
            {
            pushFollow(FOLLOW_4);
            rule__Department__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Department__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__1"


    // $ANTLR start "rule__Department__Group__1__Impl"
    // InternalRax1.g:237:1: rule__Department__Group__1__Impl : ( '-' ) ;
    public final void rule__Department__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:241:1: ( ( '-' ) )
            // InternalRax1.g:242:1: ( '-' )
            {
            // InternalRax1.g:242:1: ( '-' )
            // InternalRax1.g:243:2: '-'
            {
             before(grammarAccess.getDepartmentAccess().getHyphenMinusKeyword_1()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getDepartmentAccess().getHyphenMinusKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__1__Impl"


    // $ANTLR start "rule__Department__Group__2"
    // InternalRax1.g:252:1: rule__Department__Group__2 : rule__Department__Group__2__Impl rule__Department__Group__3 ;
    public final void rule__Department__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:256:1: ( rule__Department__Group__2__Impl rule__Department__Group__3 )
            // InternalRax1.g:257:2: rule__Department__Group__2__Impl rule__Department__Group__3
            {
            pushFollow(FOLLOW_5);
            rule__Department__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Department__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__2"


    // $ANTLR start "rule__Department__Group__2__Impl"
    // InternalRax1.g:264:1: rule__Department__Group__2__Impl : ( ( rule__Department__NameAssignment_2 ) ) ;
    public final void rule__Department__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:268:1: ( ( ( rule__Department__NameAssignment_2 ) ) )
            // InternalRax1.g:269:1: ( ( rule__Department__NameAssignment_2 ) )
            {
            // InternalRax1.g:269:1: ( ( rule__Department__NameAssignment_2 ) )
            // InternalRax1.g:270:2: ( rule__Department__NameAssignment_2 )
            {
             before(grammarAccess.getDepartmentAccess().getNameAssignment_2()); 
            // InternalRax1.g:271:2: ( rule__Department__NameAssignment_2 )
            // InternalRax1.g:271:3: rule__Department__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Department__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getDepartmentAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__2__Impl"


    // $ANTLR start "rule__Department__Group__3"
    // InternalRax1.g:279:1: rule__Department__Group__3 : rule__Department__Group__3__Impl rule__Department__Group__4 ;
    public final void rule__Department__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:283:1: ( rule__Department__Group__3__Impl rule__Department__Group__4 )
            // InternalRax1.g:284:2: rule__Department__Group__3__Impl rule__Department__Group__4
            {
            pushFollow(FOLLOW_6);
            rule__Department__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Department__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__3"


    // $ANTLR start "rule__Department__Group__3__Impl"
    // InternalRax1.g:291:1: rule__Department__Group__3__Impl : ( 'Persons:' ) ;
    public final void rule__Department__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:295:1: ( ( 'Persons:' ) )
            // InternalRax1.g:296:1: ( 'Persons:' )
            {
            // InternalRax1.g:296:1: ( 'Persons:' )
            // InternalRax1.g:297:2: 'Persons:'
            {
             before(grammarAccess.getDepartmentAccess().getPersonsKeyword_3()); 
            match(input,12,FOLLOW_2); 
             after(grammarAccess.getDepartmentAccess().getPersonsKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__3__Impl"


    // $ANTLR start "rule__Department__Group__4"
    // InternalRax1.g:306:1: rule__Department__Group__4 : rule__Department__Group__4__Impl rule__Department__Group__5 ;
    public final void rule__Department__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:310:1: ( rule__Department__Group__4__Impl rule__Department__Group__5 )
            // InternalRax1.g:311:2: rule__Department__Group__4__Impl rule__Department__Group__5
            {
            pushFollow(FOLLOW_6);
            rule__Department__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Department__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__4"


    // $ANTLR start "rule__Department__Group__4__Impl"
    // InternalRax1.g:318:1: rule__Department__Group__4__Impl : ( ( rule__Department__PersonsAssignment_4 )* ) ;
    public final void rule__Department__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:322:1: ( ( ( rule__Department__PersonsAssignment_4 )* ) )
            // InternalRax1.g:323:1: ( ( rule__Department__PersonsAssignment_4 )* )
            {
            // InternalRax1.g:323:1: ( ( rule__Department__PersonsAssignment_4 )* )
            // InternalRax1.g:324:2: ( rule__Department__PersonsAssignment_4 )*
            {
             before(grammarAccess.getDepartmentAccess().getPersonsAssignment_4()); 
            // InternalRax1.g:325:2: ( rule__Department__PersonsAssignment_4 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=RULE_ID && LA2_0<=RULE_STRING)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalRax1.g:325:3: rule__Department__PersonsAssignment_4
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Department__PersonsAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getDepartmentAccess().getPersonsAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__4__Impl"


    // $ANTLR start "rule__Department__Group__5"
    // InternalRax1.g:333:1: rule__Department__Group__5 : rule__Department__Group__5__Impl rule__Department__Group__6 ;
    public final void rule__Department__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:337:1: ( rule__Department__Group__5__Impl rule__Department__Group__6 )
            // InternalRax1.g:338:2: rule__Department__Group__5__Impl rule__Department__Group__6
            {
            pushFollow(FOLLOW_8);
            rule__Department__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Department__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__5"


    // $ANTLR start "rule__Department__Group__5__Impl"
    // InternalRax1.g:345:1: rule__Department__Group__5__Impl : ( 'Courses:' ) ;
    public final void rule__Department__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:349:1: ( ( 'Courses:' ) )
            // InternalRax1.g:350:1: ( 'Courses:' )
            {
            // InternalRax1.g:350:1: ( 'Courses:' )
            // InternalRax1.g:351:2: 'Courses:'
            {
             before(grammarAccess.getDepartmentAccess().getCoursesKeyword_5()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getDepartmentAccess().getCoursesKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__5__Impl"


    // $ANTLR start "rule__Department__Group__6"
    // InternalRax1.g:360:1: rule__Department__Group__6 : rule__Department__Group__6__Impl ;
    public final void rule__Department__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:364:1: ( rule__Department__Group__6__Impl )
            // InternalRax1.g:365:2: rule__Department__Group__6__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Department__Group__6__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__6"


    // $ANTLR start "rule__Department__Group__6__Impl"
    // InternalRax1.g:371:1: rule__Department__Group__6__Impl : ( ( rule__Department__CoursesAssignment_6 )* ) ;
    public final void rule__Department__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:375:1: ( ( ( rule__Department__CoursesAssignment_6 )* ) )
            // InternalRax1.g:376:1: ( ( rule__Department__CoursesAssignment_6 )* )
            {
            // InternalRax1.g:376:1: ( ( rule__Department__CoursesAssignment_6 )* )
            // InternalRax1.g:377:2: ( rule__Department__CoursesAssignment_6 )*
            {
             before(grammarAccess.getDepartmentAccess().getCoursesAssignment_6()); 
            // InternalRax1.g:378:2: ( rule__Department__CoursesAssignment_6 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_ID) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalRax1.g:378:3: rule__Department__CoursesAssignment_6
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__Department__CoursesAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getDepartmentAccess().getCoursesAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__Group__6__Impl"


    // $ANTLR start "rule__Course__Group__0"
    // InternalRax1.g:387:1: rule__Course__Group__0 : rule__Course__Group__0__Impl rule__Course__Group__1 ;
    public final void rule__Course__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:391:1: ( rule__Course__Group__0__Impl rule__Course__Group__1 )
            // InternalRax1.g:392:2: rule__Course__Group__0__Impl rule__Course__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__Course__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__0"


    // $ANTLR start "rule__Course__Group__0__Impl"
    // InternalRax1.g:399:1: rule__Course__Group__0__Impl : ( ( rule__Course__CodeAssignment_0 ) ) ;
    public final void rule__Course__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:403:1: ( ( ( rule__Course__CodeAssignment_0 ) ) )
            // InternalRax1.g:404:1: ( ( rule__Course__CodeAssignment_0 ) )
            {
            // InternalRax1.g:404:1: ( ( rule__Course__CodeAssignment_0 ) )
            // InternalRax1.g:405:2: ( rule__Course__CodeAssignment_0 )
            {
             before(grammarAccess.getCourseAccess().getCodeAssignment_0()); 
            // InternalRax1.g:406:2: ( rule__Course__CodeAssignment_0 )
            // InternalRax1.g:406:3: rule__Course__CodeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Course__CodeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getCourseAccess().getCodeAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__0__Impl"


    // $ANTLR start "rule__Course__Group__1"
    // InternalRax1.g:414:1: rule__Course__Group__1 : rule__Course__Group__1__Impl rule__Course__Group__2 ;
    public final void rule__Course__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:418:1: ( rule__Course__Group__1__Impl rule__Course__Group__2 )
            // InternalRax1.g:419:2: rule__Course__Group__1__Impl rule__Course__Group__2
            {
            pushFollow(FOLLOW_10);
            rule__Course__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__1"


    // $ANTLR start "rule__Course__Group__1__Impl"
    // InternalRax1.g:426:1: rule__Course__Group__1__Impl : ( '-' ) ;
    public final void rule__Course__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:430:1: ( ( '-' ) )
            // InternalRax1.g:431:1: ( '-' )
            {
            // InternalRax1.g:431:1: ( '-' )
            // InternalRax1.g:432:2: '-'
            {
             before(grammarAccess.getCourseAccess().getHyphenMinusKeyword_1()); 
            match(input,11,FOLLOW_2); 
             after(grammarAccess.getCourseAccess().getHyphenMinusKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__1__Impl"


    // $ANTLR start "rule__Course__Group__2"
    // InternalRax1.g:441:1: rule__Course__Group__2 : rule__Course__Group__2__Impl rule__Course__Group__3 ;
    public final void rule__Course__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:445:1: ( rule__Course__Group__2__Impl rule__Course__Group__3 )
            // InternalRax1.g:446:2: rule__Course__Group__2__Impl rule__Course__Group__3
            {
            pushFollow(FOLLOW_11);
            rule__Course__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__2"


    // $ANTLR start "rule__Course__Group__2__Impl"
    // InternalRax1.g:453:1: rule__Course__Group__2__Impl : ( ( rule__Course__NameAssignment_2 ) ) ;
    public final void rule__Course__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:457:1: ( ( ( rule__Course__NameAssignment_2 ) ) )
            // InternalRax1.g:458:1: ( ( rule__Course__NameAssignment_2 ) )
            {
            // InternalRax1.g:458:1: ( ( rule__Course__NameAssignment_2 ) )
            // InternalRax1.g:459:2: ( rule__Course__NameAssignment_2 )
            {
             before(grammarAccess.getCourseAccess().getNameAssignment_2()); 
            // InternalRax1.g:460:2: ( rule__Course__NameAssignment_2 )
            // InternalRax1.g:460:3: rule__Course__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Course__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getCourseAccess().getNameAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__2__Impl"


    // $ANTLR start "rule__Course__Group__3"
    // InternalRax1.g:468:1: rule__Course__Group__3 : rule__Course__Group__3__Impl rule__Course__Group__4 ;
    public final void rule__Course__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:472:1: ( rule__Course__Group__3__Impl rule__Course__Group__4 )
            // InternalRax1.g:473:2: rule__Course__Group__3__Impl rule__Course__Group__4
            {
            pushFollow(FOLLOW_10);
            rule__Course__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__3"


    // $ANTLR start "rule__Course__Group__3__Impl"
    // InternalRax1.g:480:1: rule__Course__Group__3__Impl : ( ':' ) ;
    public final void rule__Course__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:484:1: ( ( ':' ) )
            // InternalRax1.g:485:1: ( ':' )
            {
            // InternalRax1.g:485:1: ( ':' )
            // InternalRax1.g:486:2: ':'
            {
             before(grammarAccess.getCourseAccess().getColonKeyword_3()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getCourseAccess().getColonKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__3__Impl"


    // $ANTLR start "rule__Course__Group__4"
    // InternalRax1.g:495:1: rule__Course__Group__4 : rule__Course__Group__4__Impl ;
    public final void rule__Course__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:499:1: ( rule__Course__Group__4__Impl )
            // InternalRax1.g:500:2: rule__Course__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Course__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__4"


    // $ANTLR start "rule__Course__Group__4__Impl"
    // InternalRax1.g:506:1: rule__Course__Group__4__Impl : ( ( rule__Course__Group_4__0 )? ) ;
    public final void rule__Course__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:510:1: ( ( ( rule__Course__Group_4__0 )? ) )
            // InternalRax1.g:511:1: ( ( rule__Course__Group_4__0 )? )
            {
            // InternalRax1.g:511:1: ( ( rule__Course__Group_4__0 )? )
            // InternalRax1.g:512:2: ( rule__Course__Group_4__0 )?
            {
             before(grammarAccess.getCourseAccess().getGroup_4()); 
            // InternalRax1.g:513:2: ( rule__Course__Group_4__0 )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==RULE_INT) ) {
                    alt4=1;
                }
            }
            else if ( (LA4_0==RULE_STRING) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalRax1.g:513:3: rule__Course__Group_4__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Course__Group_4__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getCourseAccess().getGroup_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group__4__Impl"


    // $ANTLR start "rule__Course__Group_4__0"
    // InternalRax1.g:522:1: rule__Course__Group_4__0 : rule__Course__Group_4__0__Impl rule__Course__Group_4__1 ;
    public final void rule__Course__Group_4__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:526:1: ( rule__Course__Group_4__0__Impl rule__Course__Group_4__1 )
            // InternalRax1.g:527:2: rule__Course__Group_4__0__Impl rule__Course__Group_4__1
            {
            pushFollow(FOLLOW_12);
            rule__Course__Group_4__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group_4__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group_4__0"


    // $ANTLR start "rule__Course__Group_4__0__Impl"
    // InternalRax1.g:534:1: rule__Course__Group_4__0__Impl : ( ( rule__Course__AllocationsAssignment_4_0 ) ) ;
    public final void rule__Course__Group_4__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:538:1: ( ( ( rule__Course__AllocationsAssignment_4_0 ) ) )
            // InternalRax1.g:539:1: ( ( rule__Course__AllocationsAssignment_4_0 ) )
            {
            // InternalRax1.g:539:1: ( ( rule__Course__AllocationsAssignment_4_0 ) )
            // InternalRax1.g:540:2: ( rule__Course__AllocationsAssignment_4_0 )
            {
             before(grammarAccess.getCourseAccess().getAllocationsAssignment_4_0()); 
            // InternalRax1.g:541:2: ( rule__Course__AllocationsAssignment_4_0 )
            // InternalRax1.g:541:3: rule__Course__AllocationsAssignment_4_0
            {
            pushFollow(FOLLOW_2);
            rule__Course__AllocationsAssignment_4_0();

            state._fsp--;


            }

             after(grammarAccess.getCourseAccess().getAllocationsAssignment_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group_4__0__Impl"


    // $ANTLR start "rule__Course__Group_4__1"
    // InternalRax1.g:549:1: rule__Course__Group_4__1 : rule__Course__Group_4__1__Impl ;
    public final void rule__Course__Group_4__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:553:1: ( rule__Course__Group_4__1__Impl )
            // InternalRax1.g:554:2: rule__Course__Group_4__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Course__Group_4__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group_4__1"


    // $ANTLR start "rule__Course__Group_4__1__Impl"
    // InternalRax1.g:560:1: rule__Course__Group_4__1__Impl : ( ( rule__Course__Group_4_1__0 )* ) ;
    public final void rule__Course__Group_4__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:564:1: ( ( ( rule__Course__Group_4_1__0 )* ) )
            // InternalRax1.g:565:1: ( ( rule__Course__Group_4_1__0 )* )
            {
            // InternalRax1.g:565:1: ( ( rule__Course__Group_4_1__0 )* )
            // InternalRax1.g:566:2: ( rule__Course__Group_4_1__0 )*
            {
             before(grammarAccess.getCourseAccess().getGroup_4_1()); 
            // InternalRax1.g:567:2: ( rule__Course__Group_4_1__0 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==15) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalRax1.g:567:3: rule__Course__Group_4_1__0
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__Course__Group_4_1__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getCourseAccess().getGroup_4_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group_4__1__Impl"


    // $ANTLR start "rule__Course__Group_4_1__0"
    // InternalRax1.g:576:1: rule__Course__Group_4_1__0 : rule__Course__Group_4_1__0__Impl rule__Course__Group_4_1__1 ;
    public final void rule__Course__Group_4_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:580:1: ( rule__Course__Group_4_1__0__Impl rule__Course__Group_4_1__1 )
            // InternalRax1.g:581:2: rule__Course__Group_4_1__0__Impl rule__Course__Group_4_1__1
            {
            pushFollow(FOLLOW_10);
            rule__Course__Group_4_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group_4_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group_4_1__0"


    // $ANTLR start "rule__Course__Group_4_1__0__Impl"
    // InternalRax1.g:588:1: rule__Course__Group_4_1__0__Impl : ( ',' ) ;
    public final void rule__Course__Group_4_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:592:1: ( ( ',' ) )
            // InternalRax1.g:593:1: ( ',' )
            {
            // InternalRax1.g:593:1: ( ',' )
            // InternalRax1.g:594:2: ','
            {
             before(grammarAccess.getCourseAccess().getCommaKeyword_4_1_0()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getCourseAccess().getCommaKeyword_4_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group_4_1__0__Impl"


    // $ANTLR start "rule__Course__Group_4_1__1"
    // InternalRax1.g:603:1: rule__Course__Group_4_1__1 : rule__Course__Group_4_1__1__Impl ;
    public final void rule__Course__Group_4_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:607:1: ( rule__Course__Group_4_1__1__Impl )
            // InternalRax1.g:608:2: rule__Course__Group_4_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Course__Group_4_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group_4_1__1"


    // $ANTLR start "rule__Course__Group_4_1__1__Impl"
    // InternalRax1.g:614:1: rule__Course__Group_4_1__1__Impl : ( ( rule__Course__AllocationsAssignment_4_1_1 ) ) ;
    public final void rule__Course__Group_4_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:618:1: ( ( ( rule__Course__AllocationsAssignment_4_1_1 ) ) )
            // InternalRax1.g:619:1: ( ( rule__Course__AllocationsAssignment_4_1_1 ) )
            {
            // InternalRax1.g:619:1: ( ( rule__Course__AllocationsAssignment_4_1_1 ) )
            // InternalRax1.g:620:2: ( rule__Course__AllocationsAssignment_4_1_1 )
            {
             before(grammarAccess.getCourseAccess().getAllocationsAssignment_4_1_1()); 
            // InternalRax1.g:621:2: ( rule__Course__AllocationsAssignment_4_1_1 )
            // InternalRax1.g:621:3: rule__Course__AllocationsAssignment_4_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Course__AllocationsAssignment_4_1_1();

            state._fsp--;


            }

             after(grammarAccess.getCourseAccess().getAllocationsAssignment_4_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__Group_4_1__1__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group__0"
    // InternalRax1.g:630:1: rule__ResourceAllocation__Group__0 : rule__ResourceAllocation__Group__0__Impl rule__ResourceAllocation__Group__1 ;
    public final void rule__ResourceAllocation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:634:1: ( rule__ResourceAllocation__Group__0__Impl rule__ResourceAllocation__Group__1 )
            // InternalRax1.g:635:2: rule__ResourceAllocation__Group__0__Impl rule__ResourceAllocation__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__ResourceAllocation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ResourceAllocation__Group__0"


    // $ANTLR start "rule__ResourceAllocation__Group__0__Impl"
    // InternalRax1.g:642:1: rule__ResourceAllocation__Group__0__Impl : ( ( rule__ResourceAllocation__PersonAssignment_0 ) ) ;
    public final void rule__ResourceAllocation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:646:1: ( ( ( rule__ResourceAllocation__PersonAssignment_0 ) ) )
            // InternalRax1.g:647:1: ( ( rule__ResourceAllocation__PersonAssignment_0 ) )
            {
            // InternalRax1.g:647:1: ( ( rule__ResourceAllocation__PersonAssignment_0 ) )
            // InternalRax1.g:648:2: ( rule__ResourceAllocation__PersonAssignment_0 )
            {
             before(grammarAccess.getResourceAllocationAccess().getPersonAssignment_0()); 
            // InternalRax1.g:649:2: ( rule__ResourceAllocation__PersonAssignment_0 )
            // InternalRax1.g:649:3: rule__ResourceAllocation__PersonAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__PersonAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getPersonAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ResourceAllocation__Group__0__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group__1"
    // InternalRax1.g:657:1: rule__ResourceAllocation__Group__1 : rule__ResourceAllocation__Group__1__Impl rule__ResourceAllocation__Group__2 ;
    public final void rule__ResourceAllocation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:661:1: ( rule__ResourceAllocation__Group__1__Impl rule__ResourceAllocation__Group__2 )
            // InternalRax1.g:662:2: rule__ResourceAllocation__Group__1__Impl rule__ResourceAllocation__Group__2
            {
            pushFollow(FOLLOW_15);
            rule__ResourceAllocation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ResourceAllocation__Group__1"


    // $ANTLR start "rule__ResourceAllocation__Group__1__Impl"
    // InternalRax1.g:669:1: rule__ResourceAllocation__Group__1__Impl : ( ( rule__ResourceAllocation__PercentageAssignment_1 ) ) ;
    public final void rule__ResourceAllocation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:673:1: ( ( ( rule__ResourceAllocation__PercentageAssignment_1 ) ) )
            // InternalRax1.g:674:1: ( ( rule__ResourceAllocation__PercentageAssignment_1 ) )
            {
            // InternalRax1.g:674:1: ( ( rule__ResourceAllocation__PercentageAssignment_1 ) )
            // InternalRax1.g:675:2: ( rule__ResourceAllocation__PercentageAssignment_1 )
            {
             before(grammarAccess.getResourceAllocationAccess().getPercentageAssignment_1()); 
            // InternalRax1.g:676:2: ( rule__ResourceAllocation__PercentageAssignment_1 )
            // InternalRax1.g:676:3: rule__ResourceAllocation__PercentageAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__PercentageAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getPercentageAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ResourceAllocation__Group__1__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group__2"
    // InternalRax1.g:684:1: rule__ResourceAllocation__Group__2 : rule__ResourceAllocation__Group__2__Impl ;
    public final void rule__ResourceAllocation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:688:1: ( rule__ResourceAllocation__Group__2__Impl )
            // InternalRax1.g:689:2: rule__ResourceAllocation__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ResourceAllocation__Group__2"


    // $ANTLR start "rule__ResourceAllocation__Group__2__Impl"
    // InternalRax1.g:695:1: rule__ResourceAllocation__Group__2__Impl : ( '%' ) ;
    public final void rule__ResourceAllocation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:699:1: ( ( '%' ) )
            // InternalRax1.g:700:1: ( '%' )
            {
            // InternalRax1.g:700:1: ( '%' )
            // InternalRax1.g:701:2: '%'
            {
             before(grammarAccess.getResourceAllocationAccess().getPercentSignKeyword_2()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getPercentSignKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ResourceAllocation__Group__2__Impl"


    // $ANTLR start "rule__Department__ShortNameAssignment_0"
    // InternalRax1.g:711:1: rule__Department__ShortNameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Department__ShortNameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:715:1: ( ( RULE_ID ) )
            // InternalRax1.g:716:2: ( RULE_ID )
            {
            // InternalRax1.g:716:2: ( RULE_ID )
            // InternalRax1.g:717:3: RULE_ID
            {
             before(grammarAccess.getDepartmentAccess().getShortNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getDepartmentAccess().getShortNameIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__ShortNameAssignment_0"


    // $ANTLR start "rule__Department__NameAssignment_2"
    // InternalRax1.g:726:1: rule__Department__NameAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Department__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:730:1: ( ( RULE_STRING ) )
            // InternalRax1.g:731:2: ( RULE_STRING )
            {
            // InternalRax1.g:731:2: ( RULE_STRING )
            // InternalRax1.g:732:3: RULE_STRING
            {
             before(grammarAccess.getDepartmentAccess().getNameSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getDepartmentAccess().getNameSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__NameAssignment_2"


    // $ANTLR start "rule__Department__PersonsAssignment_4"
    // InternalRax1.g:741:1: rule__Department__PersonsAssignment_4 : ( rulePerson ) ;
    public final void rule__Department__PersonsAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:745:1: ( ( rulePerson ) )
            // InternalRax1.g:746:2: ( rulePerson )
            {
            // InternalRax1.g:746:2: ( rulePerson )
            // InternalRax1.g:747:3: rulePerson
            {
             before(grammarAccess.getDepartmentAccess().getPersonsPersonParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            rulePerson();

            state._fsp--;

             after(grammarAccess.getDepartmentAccess().getPersonsPersonParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__PersonsAssignment_4"


    // $ANTLR start "rule__Department__CoursesAssignment_6"
    // InternalRax1.g:756:1: rule__Department__CoursesAssignment_6 : ( ruleCourse ) ;
    public final void rule__Department__CoursesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:760:1: ( ( ruleCourse ) )
            // InternalRax1.g:761:2: ( ruleCourse )
            {
            // InternalRax1.g:761:2: ( ruleCourse )
            // InternalRax1.g:762:3: ruleCourse
            {
             before(grammarAccess.getDepartmentAccess().getCoursesCourseParserRuleCall_6_0()); 
            pushFollow(FOLLOW_2);
            ruleCourse();

            state._fsp--;

             after(grammarAccess.getDepartmentAccess().getCoursesCourseParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Department__CoursesAssignment_6"


    // $ANTLR start "rule__Person__NameAssignment"
    // InternalRax1.g:771:1: rule__Person__NameAssignment : ( ruleNameOrString ) ;
    public final void rule__Person__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:775:1: ( ( ruleNameOrString ) )
            // InternalRax1.g:776:2: ( ruleNameOrString )
            {
            // InternalRax1.g:776:2: ( ruleNameOrString )
            // InternalRax1.g:777:3: ruleNameOrString
            {
             before(grammarAccess.getPersonAccess().getNameNameOrStringParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleNameOrString();

            state._fsp--;

             after(grammarAccess.getPersonAccess().getNameNameOrStringParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Person__NameAssignment"


    // $ANTLR start "rule__Course__CodeAssignment_0"
    // InternalRax1.g:786:1: rule__Course__CodeAssignment_0 : ( RULE_ID ) ;
    public final void rule__Course__CodeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:790:1: ( ( RULE_ID ) )
            // InternalRax1.g:791:2: ( RULE_ID )
            {
            // InternalRax1.g:791:2: ( RULE_ID )
            // InternalRax1.g:792:3: RULE_ID
            {
             before(grammarAccess.getCourseAccess().getCodeIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getCourseAccess().getCodeIDTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__CodeAssignment_0"


    // $ANTLR start "rule__Course__NameAssignment_2"
    // InternalRax1.g:801:1: rule__Course__NameAssignment_2 : ( ruleNameOrString ) ;
    public final void rule__Course__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:805:1: ( ( ruleNameOrString ) )
            // InternalRax1.g:806:2: ( ruleNameOrString )
            {
            // InternalRax1.g:806:2: ( ruleNameOrString )
            // InternalRax1.g:807:3: ruleNameOrString
            {
             before(grammarAccess.getCourseAccess().getNameNameOrStringParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleNameOrString();

            state._fsp--;

             after(grammarAccess.getCourseAccess().getNameNameOrStringParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__NameAssignment_2"


    // $ANTLR start "rule__Course__AllocationsAssignment_4_0"
    // InternalRax1.g:816:1: rule__Course__AllocationsAssignment_4_0 : ( ruleResourceAllocation ) ;
    public final void rule__Course__AllocationsAssignment_4_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:820:1: ( ( ruleResourceAllocation ) )
            // InternalRax1.g:821:2: ( ruleResourceAllocation )
            {
            // InternalRax1.g:821:2: ( ruleResourceAllocation )
            // InternalRax1.g:822:3: ruleResourceAllocation
            {
             before(grammarAccess.getCourseAccess().getAllocationsResourceAllocationParserRuleCall_4_0_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceAllocation();

            state._fsp--;

             after(grammarAccess.getCourseAccess().getAllocationsResourceAllocationParserRuleCall_4_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__AllocationsAssignment_4_0"


    // $ANTLR start "rule__Course__AllocationsAssignment_4_1_1"
    // InternalRax1.g:831:1: rule__Course__AllocationsAssignment_4_1_1 : ( ruleResourceAllocation ) ;
    public final void rule__Course__AllocationsAssignment_4_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:835:1: ( ( ruleResourceAllocation ) )
            // InternalRax1.g:836:2: ( ruleResourceAllocation )
            {
            // InternalRax1.g:836:2: ( ruleResourceAllocation )
            // InternalRax1.g:837:3: ruleResourceAllocation
            {
             before(grammarAccess.getCourseAccess().getAllocationsResourceAllocationParserRuleCall_4_1_1_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceAllocation();

            state._fsp--;

             after(grammarAccess.getCourseAccess().getAllocationsResourceAllocationParserRuleCall_4_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Course__AllocationsAssignment_4_1_1"


    // $ANTLR start "rule__ResourceAllocation__PersonAssignment_0"
    // InternalRax1.g:846:1: rule__ResourceAllocation__PersonAssignment_0 : ( ( ruleNameOrString ) ) ;
    public final void rule__ResourceAllocation__PersonAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:850:1: ( ( ( ruleNameOrString ) ) )
            // InternalRax1.g:851:2: ( ( ruleNameOrString ) )
            {
            // InternalRax1.g:851:2: ( ( ruleNameOrString ) )
            // InternalRax1.g:852:3: ( ruleNameOrString )
            {
             before(grammarAccess.getResourceAllocationAccess().getPersonPersonCrossReference_0_0()); 
            // InternalRax1.g:853:3: ( ruleNameOrString )
            // InternalRax1.g:854:4: ruleNameOrString
            {
             before(grammarAccess.getResourceAllocationAccess().getPersonPersonNameOrStringParserRuleCall_0_0_1()); 
            pushFollow(FOLLOW_2);
            ruleNameOrString();

            state._fsp--;

             after(grammarAccess.getResourceAllocationAccess().getPersonPersonNameOrStringParserRuleCall_0_0_1()); 

            }

             after(grammarAccess.getResourceAllocationAccess().getPersonPersonCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ResourceAllocation__PersonAssignment_0"


    // $ANTLR start "rule__ResourceAllocation__PercentageAssignment_1"
    // InternalRax1.g:865:1: rule__ResourceAllocation__PercentageAssignment_1 : ( RULE_INT ) ;
    public final void rule__ResourceAllocation__PercentageAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax1.g:869:1: ( ( RULE_INT ) )
            // InternalRax1.g:870:2: ( RULE_INT )
            {
            // InternalRax1.g:870:2: ( RULE_INT )
            // InternalRax1.g:871:3: RULE_INT
            {
             before(grammarAccess.getResourceAllocationAccess().getPercentageINTTerminalRuleCall_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getPercentageINTTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ResourceAllocation__PercentageAssignment_1"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000010000L});

}