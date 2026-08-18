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
import tdt4250.ra.services.Rax2GrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalRax2Parser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'-'", "'Persons:'", "'Courses:'", "'Allocations:'", "'requires'", "','", "'for'", "'%'", "'.'", "'is'", "'in'", "'as'"
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

    	public void setGrammarAccess(Rax2GrammarAccess grammarAccess) {
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
    // InternalRax2.g:53:1: entryRuleDepartment : ruleDepartment EOF ;
    public final void entryRuleDepartment() throws RecognitionException {
        try {
            // InternalRax2.g:54:1: ( ruleDepartment EOF )
            // InternalRax2.g:55:1: ruleDepartment EOF
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
    // InternalRax2.g:62:1: ruleDepartment : ( ( rule__Department__Group__0 ) ) ;
    public final void ruleDepartment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:66:2: ( ( ( rule__Department__Group__0 ) ) )
            // InternalRax2.g:67:2: ( ( rule__Department__Group__0 ) )
            {
            // InternalRax2.g:67:2: ( ( rule__Department__Group__0 ) )
            // InternalRax2.g:68:3: ( rule__Department__Group__0 )
            {
             before(grammarAccess.getDepartmentAccess().getGroup()); 
            // InternalRax2.g:69:3: ( rule__Department__Group__0 )
            // InternalRax2.g:69:4: rule__Department__Group__0
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
    // InternalRax2.g:78:1: entryRuleNameOrString : ruleNameOrString EOF ;
    public final void entryRuleNameOrString() throws RecognitionException {
        try {
            // InternalRax2.g:79:1: ( ruleNameOrString EOF )
            // InternalRax2.g:80:1: ruleNameOrString EOF
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
    // InternalRax2.g:87:1: ruleNameOrString : ( ( rule__NameOrString__Alternatives ) ) ;
    public final void ruleNameOrString() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:91:2: ( ( ( rule__NameOrString__Alternatives ) ) )
            // InternalRax2.g:92:2: ( ( rule__NameOrString__Alternatives ) )
            {
            // InternalRax2.g:92:2: ( ( rule__NameOrString__Alternatives ) )
            // InternalRax2.g:93:3: ( rule__NameOrString__Alternatives )
            {
             before(grammarAccess.getNameOrStringAccess().getAlternatives()); 
            // InternalRax2.g:94:3: ( rule__NameOrString__Alternatives )
            // InternalRax2.g:94:4: rule__NameOrString__Alternatives
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
    // InternalRax2.g:103:1: entryRulePerson : rulePerson EOF ;
    public final void entryRulePerson() throws RecognitionException {
        try {
            // InternalRax2.g:104:1: ( rulePerson EOF )
            // InternalRax2.g:105:1: rulePerson EOF
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
    // InternalRax2.g:112:1: rulePerson : ( ( rule__Person__NameAssignment ) ) ;
    public final void rulePerson() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:116:2: ( ( ( rule__Person__NameAssignment ) ) )
            // InternalRax2.g:117:2: ( ( rule__Person__NameAssignment ) )
            {
            // InternalRax2.g:117:2: ( ( rule__Person__NameAssignment ) )
            // InternalRax2.g:118:3: ( rule__Person__NameAssignment )
            {
             before(grammarAccess.getPersonAccess().getNameAssignment()); 
            // InternalRax2.g:119:3: ( rule__Person__NameAssignment )
            // InternalRax2.g:119:4: rule__Person__NameAssignment
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
    // InternalRax2.g:128:1: entryRuleCourse : ruleCourse EOF ;
    public final void entryRuleCourse() throws RecognitionException {
        try {
            // InternalRax2.g:129:1: ( ruleCourse EOF )
            // InternalRax2.g:130:1: ruleCourse EOF
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
    // InternalRax2.g:137:1: ruleCourse : ( ( rule__Course__Group__0 ) ) ;
    public final void ruleCourse() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:141:2: ( ( ( rule__Course__Group__0 ) ) )
            // InternalRax2.g:142:2: ( ( rule__Course__Group__0 ) )
            {
            // InternalRax2.g:142:2: ( ( rule__Course__Group__0 ) )
            // InternalRax2.g:143:3: ( rule__Course__Group__0 )
            {
             before(grammarAccess.getCourseAccess().getGroup()); 
            // InternalRax2.g:144:3: ( rule__Course__Group__0 )
            // InternalRax2.g:144:4: rule__Course__Group__0
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


    // $ANTLR start "entryRuleRole"
    // InternalRax2.g:153:1: entryRuleRole : ruleRole EOF ;
    public final void entryRuleRole() throws RecognitionException {
        try {
            // InternalRax2.g:154:1: ( ruleRole EOF )
            // InternalRax2.g:155:1: ruleRole EOF
            {
             before(grammarAccess.getRoleRule()); 
            pushFollow(FOLLOW_1);
            ruleRole();

            state._fsp--;

             after(grammarAccess.getRoleRule()); 
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
    // $ANTLR end "entryRuleRole"


    // $ANTLR start "ruleRole"
    // InternalRax2.g:162:1: ruleRole : ( ( rule__Role__Group__0 ) ) ;
    public final void ruleRole() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:166:2: ( ( ( rule__Role__Group__0 ) ) )
            // InternalRax2.g:167:2: ( ( rule__Role__Group__0 ) )
            {
            // InternalRax2.g:167:2: ( ( rule__Role__Group__0 ) )
            // InternalRax2.g:168:3: ( rule__Role__Group__0 )
            {
             before(grammarAccess.getRoleAccess().getGroup()); 
            // InternalRax2.g:169:3: ( rule__Role__Group__0 )
            // InternalRax2.g:169:4: rule__Role__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Role__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRoleAccess().getGroup()); 

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
    // $ANTLR end "ruleRole"


    // $ANTLR start "entryRuleFactorAsPercentage"
    // InternalRax2.g:178:1: entryRuleFactorAsPercentage : ruleFactorAsPercentage EOF ;
    public final void entryRuleFactorAsPercentage() throws RecognitionException {
        try {
            // InternalRax2.g:179:1: ( ruleFactorAsPercentage EOF )
            // InternalRax2.g:180:1: ruleFactorAsPercentage EOF
            {
             before(grammarAccess.getFactorAsPercentageRule()); 
            pushFollow(FOLLOW_1);
            ruleFactorAsPercentage();

            state._fsp--;

             after(grammarAccess.getFactorAsPercentageRule()); 
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
    // $ANTLR end "entryRuleFactorAsPercentage"


    // $ANTLR start "ruleFactorAsPercentage"
    // InternalRax2.g:187:1: ruleFactorAsPercentage : ( ( rule__FactorAsPercentage__Group__0 ) ) ;
    public final void ruleFactorAsPercentage() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:191:2: ( ( ( rule__FactorAsPercentage__Group__0 ) ) )
            // InternalRax2.g:192:2: ( ( rule__FactorAsPercentage__Group__0 ) )
            {
            // InternalRax2.g:192:2: ( ( rule__FactorAsPercentage__Group__0 ) )
            // InternalRax2.g:193:3: ( rule__FactorAsPercentage__Group__0 )
            {
             before(grammarAccess.getFactorAsPercentageAccess().getGroup()); 
            // InternalRax2.g:194:3: ( rule__FactorAsPercentage__Group__0 )
            // InternalRax2.g:194:4: rule__FactorAsPercentage__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FactorAsPercentage__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFactorAsPercentageAccess().getGroup()); 

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
    // $ANTLR end "ruleFactorAsPercentage"


    // $ANTLR start "entryRuleResourceAllocation"
    // InternalRax2.g:203:1: entryRuleResourceAllocation : ruleResourceAllocation EOF ;
    public final void entryRuleResourceAllocation() throws RecognitionException {
        try {
            // InternalRax2.g:204:1: ( ruleResourceAllocation EOF )
            // InternalRax2.g:205:1: ruleResourceAllocation EOF
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
    // InternalRax2.g:212:1: ruleResourceAllocation : ( ( rule__ResourceAllocation__Group__0 ) ) ;
    public final void ruleResourceAllocation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:216:2: ( ( ( rule__ResourceAllocation__Group__0 ) ) )
            // InternalRax2.g:217:2: ( ( rule__ResourceAllocation__Group__0 ) )
            {
            // InternalRax2.g:217:2: ( ( rule__ResourceAllocation__Group__0 ) )
            // InternalRax2.g:218:3: ( rule__ResourceAllocation__Group__0 )
            {
             before(grammarAccess.getResourceAllocationAccess().getGroup()); 
            // InternalRax2.g:219:3: ( rule__ResourceAllocation__Group__0 )
            // InternalRax2.g:219:4: rule__ResourceAllocation__Group__0
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
    // InternalRax2.g:227:1: rule__NameOrString__Alternatives : ( ( RULE_ID ) | ( RULE_STRING ) );
    public final void rule__NameOrString__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:231:1: ( ( RULE_ID ) | ( RULE_STRING ) )
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
                    // InternalRax2.g:232:2: ( RULE_ID )
                    {
                    // InternalRax2.g:232:2: ( RULE_ID )
                    // InternalRax2.g:233:3: RULE_ID
                    {
                     before(grammarAccess.getNameOrStringAccess().getIDTerminalRuleCall_0()); 
                    match(input,RULE_ID,FOLLOW_2); 
                     after(grammarAccess.getNameOrStringAccess().getIDTerminalRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalRax2.g:238:2: ( RULE_STRING )
                    {
                    // InternalRax2.g:238:2: ( RULE_STRING )
                    // InternalRax2.g:239:3: RULE_STRING
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


    // $ANTLR start "rule__ResourceAllocation__Alternatives_2"
    // InternalRax2.g:248:1: rule__ResourceAllocation__Alternatives_2 : ( ( ( rule__ResourceAllocation__Group_2_0__0 ) ) | ( ( rule__ResourceAllocation__Group_2_1__0 ) ) );
    public final void rule__ResourceAllocation__Alternatives_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:252:1: ( ( ( rule__ResourceAllocation__Group_2_0__0 ) ) | ( ( rule__ResourceAllocation__Group_2_1__0 ) ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_INT) ) {
                alt2=1;
            }
            else if ( (LA2_0==21) ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalRax2.g:253:2: ( ( rule__ResourceAllocation__Group_2_0__0 ) )
                    {
                    // InternalRax2.g:253:2: ( ( rule__ResourceAllocation__Group_2_0__0 ) )
                    // InternalRax2.g:254:3: ( rule__ResourceAllocation__Group_2_0__0 )
                    {
                     before(grammarAccess.getResourceAllocationAccess().getGroup_2_0()); 
                    // InternalRax2.g:255:3: ( rule__ResourceAllocation__Group_2_0__0 )
                    // InternalRax2.g:255:4: rule__ResourceAllocation__Group_2_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ResourceAllocation__Group_2_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getResourceAllocationAccess().getGroup_2_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalRax2.g:259:2: ( ( rule__ResourceAllocation__Group_2_1__0 ) )
                    {
                    // InternalRax2.g:259:2: ( ( rule__ResourceAllocation__Group_2_1__0 ) )
                    // InternalRax2.g:260:3: ( rule__ResourceAllocation__Group_2_1__0 )
                    {
                     before(grammarAccess.getResourceAllocationAccess().getGroup_2_1()); 
                    // InternalRax2.g:261:3: ( rule__ResourceAllocation__Group_2_1__0 )
                    // InternalRax2.g:261:4: rule__ResourceAllocation__Group_2_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__ResourceAllocation__Group_2_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getResourceAllocationAccess().getGroup_2_1()); 

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
    // $ANTLR end "rule__ResourceAllocation__Alternatives_2"


    // $ANTLR start "rule__Department__Group__0"
    // InternalRax2.g:269:1: rule__Department__Group__0 : rule__Department__Group__0__Impl rule__Department__Group__1 ;
    public final void rule__Department__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:273:1: ( rule__Department__Group__0__Impl rule__Department__Group__1 )
            // InternalRax2.g:274:2: rule__Department__Group__0__Impl rule__Department__Group__1
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
    // InternalRax2.g:281:1: rule__Department__Group__0__Impl : ( ( rule__Department__ShortNameAssignment_0 ) ) ;
    public final void rule__Department__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:285:1: ( ( ( rule__Department__ShortNameAssignment_0 ) ) )
            // InternalRax2.g:286:1: ( ( rule__Department__ShortNameAssignment_0 ) )
            {
            // InternalRax2.g:286:1: ( ( rule__Department__ShortNameAssignment_0 ) )
            // InternalRax2.g:287:2: ( rule__Department__ShortNameAssignment_0 )
            {
             before(grammarAccess.getDepartmentAccess().getShortNameAssignment_0()); 
            // InternalRax2.g:288:2: ( rule__Department__ShortNameAssignment_0 )
            // InternalRax2.g:288:3: rule__Department__ShortNameAssignment_0
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
    // InternalRax2.g:296:1: rule__Department__Group__1 : rule__Department__Group__1__Impl rule__Department__Group__2 ;
    public final void rule__Department__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:300:1: ( rule__Department__Group__1__Impl rule__Department__Group__2 )
            // InternalRax2.g:301:2: rule__Department__Group__1__Impl rule__Department__Group__2
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
    // InternalRax2.g:308:1: rule__Department__Group__1__Impl : ( '-' ) ;
    public final void rule__Department__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:312:1: ( ( '-' ) )
            // InternalRax2.g:313:1: ( '-' )
            {
            // InternalRax2.g:313:1: ( '-' )
            // InternalRax2.g:314:2: '-'
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
    // InternalRax2.g:323:1: rule__Department__Group__2 : rule__Department__Group__2__Impl rule__Department__Group__3 ;
    public final void rule__Department__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:327:1: ( rule__Department__Group__2__Impl rule__Department__Group__3 )
            // InternalRax2.g:328:2: rule__Department__Group__2__Impl rule__Department__Group__3
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
    // InternalRax2.g:335:1: rule__Department__Group__2__Impl : ( ( rule__Department__NameAssignment_2 ) ) ;
    public final void rule__Department__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:339:1: ( ( ( rule__Department__NameAssignment_2 ) ) )
            // InternalRax2.g:340:1: ( ( rule__Department__NameAssignment_2 ) )
            {
            // InternalRax2.g:340:1: ( ( rule__Department__NameAssignment_2 ) )
            // InternalRax2.g:341:2: ( rule__Department__NameAssignment_2 )
            {
             before(grammarAccess.getDepartmentAccess().getNameAssignment_2()); 
            // InternalRax2.g:342:2: ( rule__Department__NameAssignment_2 )
            // InternalRax2.g:342:3: rule__Department__NameAssignment_2
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
    // InternalRax2.g:350:1: rule__Department__Group__3 : rule__Department__Group__3__Impl rule__Department__Group__4 ;
    public final void rule__Department__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:354:1: ( rule__Department__Group__3__Impl rule__Department__Group__4 )
            // InternalRax2.g:355:2: rule__Department__Group__3__Impl rule__Department__Group__4
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
    // InternalRax2.g:362:1: rule__Department__Group__3__Impl : ( 'Persons:' ) ;
    public final void rule__Department__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:366:1: ( ( 'Persons:' ) )
            // InternalRax2.g:367:1: ( 'Persons:' )
            {
            // InternalRax2.g:367:1: ( 'Persons:' )
            // InternalRax2.g:368:2: 'Persons:'
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
    // InternalRax2.g:377:1: rule__Department__Group__4 : rule__Department__Group__4__Impl rule__Department__Group__5 ;
    public final void rule__Department__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:381:1: ( rule__Department__Group__4__Impl rule__Department__Group__5 )
            // InternalRax2.g:382:2: rule__Department__Group__4__Impl rule__Department__Group__5
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
    // InternalRax2.g:389:1: rule__Department__Group__4__Impl : ( ( rule__Department__StaffAssignment_4 )* ) ;
    public final void rule__Department__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:393:1: ( ( ( rule__Department__StaffAssignment_4 )* ) )
            // InternalRax2.g:394:1: ( ( rule__Department__StaffAssignment_4 )* )
            {
            // InternalRax2.g:394:1: ( ( rule__Department__StaffAssignment_4 )* )
            // InternalRax2.g:395:2: ( rule__Department__StaffAssignment_4 )*
            {
             before(grammarAccess.getDepartmentAccess().getStaffAssignment_4()); 
            // InternalRax2.g:396:2: ( rule__Department__StaffAssignment_4 )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=RULE_ID && LA3_0<=RULE_STRING)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalRax2.g:396:3: rule__Department__StaffAssignment_4
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Department__StaffAssignment_4();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

             after(grammarAccess.getDepartmentAccess().getStaffAssignment_4()); 

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
    // InternalRax2.g:404:1: rule__Department__Group__5 : rule__Department__Group__5__Impl rule__Department__Group__6 ;
    public final void rule__Department__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:408:1: ( rule__Department__Group__5__Impl rule__Department__Group__6 )
            // InternalRax2.g:409:2: rule__Department__Group__5__Impl rule__Department__Group__6
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
    // InternalRax2.g:416:1: rule__Department__Group__5__Impl : ( 'Courses:' ) ;
    public final void rule__Department__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:420:1: ( ( 'Courses:' ) )
            // InternalRax2.g:421:1: ( 'Courses:' )
            {
            // InternalRax2.g:421:1: ( 'Courses:' )
            // InternalRax2.g:422:2: 'Courses:'
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
    // InternalRax2.g:431:1: rule__Department__Group__6 : rule__Department__Group__6__Impl rule__Department__Group__7 ;
    public final void rule__Department__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:435:1: ( rule__Department__Group__6__Impl rule__Department__Group__7 )
            // InternalRax2.g:436:2: rule__Department__Group__6__Impl rule__Department__Group__7
            {
            pushFollow(FOLLOW_8);
            rule__Department__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Department__Group__7();

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
    // InternalRax2.g:443:1: rule__Department__Group__6__Impl : ( ( rule__Department__CoursesAssignment_6 )* ) ;
    public final void rule__Department__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:447:1: ( ( ( rule__Department__CoursesAssignment_6 )* ) )
            // InternalRax2.g:448:1: ( ( rule__Department__CoursesAssignment_6 )* )
            {
            // InternalRax2.g:448:1: ( ( rule__Department__CoursesAssignment_6 )* )
            // InternalRax2.g:449:2: ( rule__Department__CoursesAssignment_6 )*
            {
             before(grammarAccess.getDepartmentAccess().getCoursesAssignment_6()); 
            // InternalRax2.g:450:2: ( rule__Department__CoursesAssignment_6 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_ID) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalRax2.g:450:3: rule__Department__CoursesAssignment_6
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__Department__CoursesAssignment_6();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
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


    // $ANTLR start "rule__Department__Group__7"
    // InternalRax2.g:458:1: rule__Department__Group__7 : rule__Department__Group__7__Impl rule__Department__Group__8 ;
    public final void rule__Department__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:462:1: ( rule__Department__Group__7__Impl rule__Department__Group__8 )
            // InternalRax2.g:463:2: rule__Department__Group__7__Impl rule__Department__Group__8
            {
            pushFollow(FOLLOW_10);
            rule__Department__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Department__Group__8();

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
    // $ANTLR end "rule__Department__Group__7"


    // $ANTLR start "rule__Department__Group__7__Impl"
    // InternalRax2.g:470:1: rule__Department__Group__7__Impl : ( 'Allocations:' ) ;
    public final void rule__Department__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:474:1: ( ( 'Allocations:' ) )
            // InternalRax2.g:475:1: ( 'Allocations:' )
            {
            // InternalRax2.g:475:1: ( 'Allocations:' )
            // InternalRax2.g:476:2: 'Allocations:'
            {
             before(grammarAccess.getDepartmentAccess().getAllocationsKeyword_7()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getDepartmentAccess().getAllocationsKeyword_7()); 

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
    // $ANTLR end "rule__Department__Group__7__Impl"


    // $ANTLR start "rule__Department__Group__8"
    // InternalRax2.g:485:1: rule__Department__Group__8 : rule__Department__Group__8__Impl ;
    public final void rule__Department__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:489:1: ( rule__Department__Group__8__Impl )
            // InternalRax2.g:490:2: rule__Department__Group__8__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Department__Group__8__Impl();

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
    // $ANTLR end "rule__Department__Group__8"


    // $ANTLR start "rule__Department__Group__8__Impl"
    // InternalRax2.g:496:1: rule__Department__Group__8__Impl : ( ( rule__Department__ResourceAllocationsAssignment_8 )* ) ;
    public final void rule__Department__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:500:1: ( ( ( rule__Department__ResourceAllocationsAssignment_8 )* ) )
            // InternalRax2.g:501:1: ( ( rule__Department__ResourceAllocationsAssignment_8 )* )
            {
            // InternalRax2.g:501:1: ( ( rule__Department__ResourceAllocationsAssignment_8 )* )
            // InternalRax2.g:502:2: ( rule__Department__ResourceAllocationsAssignment_8 )*
            {
             before(grammarAccess.getDepartmentAccess().getResourceAllocationsAssignment_8()); 
            // InternalRax2.g:503:2: ( rule__Department__ResourceAllocationsAssignment_8 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=RULE_ID && LA5_0<=RULE_STRING)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalRax2.g:503:3: rule__Department__ResourceAllocationsAssignment_8
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__Department__ResourceAllocationsAssignment_8();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getDepartmentAccess().getResourceAllocationsAssignment_8()); 

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
    // $ANTLR end "rule__Department__Group__8__Impl"


    // $ANTLR start "rule__Course__Group__0"
    // InternalRax2.g:512:1: rule__Course__Group__0 : rule__Course__Group__0__Impl rule__Course__Group__1 ;
    public final void rule__Course__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:516:1: ( rule__Course__Group__0__Impl rule__Course__Group__1 )
            // InternalRax2.g:517:2: rule__Course__Group__0__Impl rule__Course__Group__1
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
    // InternalRax2.g:524:1: rule__Course__Group__0__Impl : ( ( rule__Course__CodeAssignment_0 ) ) ;
    public final void rule__Course__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:528:1: ( ( ( rule__Course__CodeAssignment_0 ) ) )
            // InternalRax2.g:529:1: ( ( rule__Course__CodeAssignment_0 ) )
            {
            // InternalRax2.g:529:1: ( ( rule__Course__CodeAssignment_0 ) )
            // InternalRax2.g:530:2: ( rule__Course__CodeAssignment_0 )
            {
             before(grammarAccess.getCourseAccess().getCodeAssignment_0()); 
            // InternalRax2.g:531:2: ( rule__Course__CodeAssignment_0 )
            // InternalRax2.g:531:3: rule__Course__CodeAssignment_0
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
    // InternalRax2.g:539:1: rule__Course__Group__1 : rule__Course__Group__1__Impl rule__Course__Group__2 ;
    public final void rule__Course__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:543:1: ( rule__Course__Group__1__Impl rule__Course__Group__2 )
            // InternalRax2.g:544:2: rule__Course__Group__1__Impl rule__Course__Group__2
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
    // InternalRax2.g:551:1: rule__Course__Group__1__Impl : ( '-' ) ;
    public final void rule__Course__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:555:1: ( ( '-' ) )
            // InternalRax2.g:556:1: ( '-' )
            {
            // InternalRax2.g:556:1: ( '-' )
            // InternalRax2.g:557:2: '-'
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
    // InternalRax2.g:566:1: rule__Course__Group__2 : rule__Course__Group__2__Impl rule__Course__Group__3 ;
    public final void rule__Course__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:570:1: ( rule__Course__Group__2__Impl rule__Course__Group__3 )
            // InternalRax2.g:571:2: rule__Course__Group__2__Impl rule__Course__Group__3
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
    // InternalRax2.g:578:1: rule__Course__Group__2__Impl : ( ( rule__Course__NameAssignment_2 ) ) ;
    public final void rule__Course__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:582:1: ( ( ( rule__Course__NameAssignment_2 ) ) )
            // InternalRax2.g:583:1: ( ( rule__Course__NameAssignment_2 ) )
            {
            // InternalRax2.g:583:1: ( ( rule__Course__NameAssignment_2 ) )
            // InternalRax2.g:584:2: ( rule__Course__NameAssignment_2 )
            {
             before(grammarAccess.getCourseAccess().getNameAssignment_2()); 
            // InternalRax2.g:585:2: ( rule__Course__NameAssignment_2 )
            // InternalRax2.g:585:3: rule__Course__NameAssignment_2
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
    // InternalRax2.g:593:1: rule__Course__Group__3 : rule__Course__Group__3__Impl ;
    public final void rule__Course__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:597:1: ( rule__Course__Group__3__Impl )
            // InternalRax2.g:598:2: rule__Course__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Course__Group__3__Impl();

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
    // InternalRax2.g:604:1: rule__Course__Group__3__Impl : ( ( rule__Course__Group_3__0 )? ) ;
    public final void rule__Course__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:608:1: ( ( ( rule__Course__Group_3__0 )? ) )
            // InternalRax2.g:609:1: ( ( rule__Course__Group_3__0 )? )
            {
            // InternalRax2.g:609:1: ( ( rule__Course__Group_3__0 )? )
            // InternalRax2.g:610:2: ( rule__Course__Group_3__0 )?
            {
             before(grammarAccess.getCourseAccess().getGroup_3()); 
            // InternalRax2.g:611:2: ( rule__Course__Group_3__0 )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==15) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalRax2.g:611:3: rule__Course__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Course__Group_3__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getCourseAccess().getGroup_3()); 

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


    // $ANTLR start "rule__Course__Group_3__0"
    // InternalRax2.g:620:1: rule__Course__Group_3__0 : rule__Course__Group_3__0__Impl rule__Course__Group_3__1 ;
    public final void rule__Course__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:624:1: ( rule__Course__Group_3__0__Impl rule__Course__Group_3__1 )
            // InternalRax2.g:625:2: rule__Course__Group_3__0__Impl rule__Course__Group_3__1
            {
            pushFollow(FOLLOW_12);
            rule__Course__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group_3__1();

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
    // $ANTLR end "rule__Course__Group_3__0"


    // $ANTLR start "rule__Course__Group_3__0__Impl"
    // InternalRax2.g:632:1: rule__Course__Group_3__0__Impl : ( 'requires' ) ;
    public final void rule__Course__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:636:1: ( ( 'requires' ) )
            // InternalRax2.g:637:1: ( 'requires' )
            {
            // InternalRax2.g:637:1: ( 'requires' )
            // InternalRax2.g:638:2: 'requires'
            {
             before(grammarAccess.getCourseAccess().getRequiresKeyword_3_0()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getCourseAccess().getRequiresKeyword_3_0()); 

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
    // $ANTLR end "rule__Course__Group_3__0__Impl"


    // $ANTLR start "rule__Course__Group_3__1"
    // InternalRax2.g:647:1: rule__Course__Group_3__1 : rule__Course__Group_3__1__Impl rule__Course__Group_3__2 ;
    public final void rule__Course__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:651:1: ( rule__Course__Group_3__1__Impl rule__Course__Group_3__2 )
            // InternalRax2.g:652:2: rule__Course__Group_3__1__Impl rule__Course__Group_3__2
            {
            pushFollow(FOLLOW_13);
            rule__Course__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group_3__2();

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
    // $ANTLR end "rule__Course__Group_3__1"


    // $ANTLR start "rule__Course__Group_3__1__Impl"
    // InternalRax2.g:659:1: rule__Course__Group_3__1__Impl : ( ( rule__Course__RolesAssignment_3_1 ) ) ;
    public final void rule__Course__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:663:1: ( ( ( rule__Course__RolesAssignment_3_1 ) ) )
            // InternalRax2.g:664:1: ( ( rule__Course__RolesAssignment_3_1 ) )
            {
            // InternalRax2.g:664:1: ( ( rule__Course__RolesAssignment_3_1 ) )
            // InternalRax2.g:665:2: ( rule__Course__RolesAssignment_3_1 )
            {
             before(grammarAccess.getCourseAccess().getRolesAssignment_3_1()); 
            // InternalRax2.g:666:2: ( rule__Course__RolesAssignment_3_1 )
            // InternalRax2.g:666:3: rule__Course__RolesAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Course__RolesAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getCourseAccess().getRolesAssignment_3_1()); 

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
    // $ANTLR end "rule__Course__Group_3__1__Impl"


    // $ANTLR start "rule__Course__Group_3__2"
    // InternalRax2.g:674:1: rule__Course__Group_3__2 : rule__Course__Group_3__2__Impl ;
    public final void rule__Course__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:678:1: ( rule__Course__Group_3__2__Impl )
            // InternalRax2.g:679:2: rule__Course__Group_3__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Course__Group_3__2__Impl();

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
    // $ANTLR end "rule__Course__Group_3__2"


    // $ANTLR start "rule__Course__Group_3__2__Impl"
    // InternalRax2.g:685:1: rule__Course__Group_3__2__Impl : ( ( rule__Course__Group_3_2__0 )* ) ;
    public final void rule__Course__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:689:1: ( ( ( rule__Course__Group_3_2__0 )* ) )
            // InternalRax2.g:690:1: ( ( rule__Course__Group_3_2__0 )* )
            {
            // InternalRax2.g:690:1: ( ( rule__Course__Group_3_2__0 )* )
            // InternalRax2.g:691:2: ( rule__Course__Group_3_2__0 )*
            {
             before(grammarAccess.getCourseAccess().getGroup_3_2()); 
            // InternalRax2.g:692:2: ( rule__Course__Group_3_2__0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==16) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalRax2.g:692:3: rule__Course__Group_3_2__0
            	    {
            	    pushFollow(FOLLOW_14);
            	    rule__Course__Group_3_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getCourseAccess().getGroup_3_2()); 

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
    // $ANTLR end "rule__Course__Group_3__2__Impl"


    // $ANTLR start "rule__Course__Group_3_2__0"
    // InternalRax2.g:701:1: rule__Course__Group_3_2__0 : rule__Course__Group_3_2__0__Impl rule__Course__Group_3_2__1 ;
    public final void rule__Course__Group_3_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:705:1: ( rule__Course__Group_3_2__0__Impl rule__Course__Group_3_2__1 )
            // InternalRax2.g:706:2: rule__Course__Group_3_2__0__Impl rule__Course__Group_3_2__1
            {
            pushFollow(FOLLOW_12);
            rule__Course__Group_3_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Course__Group_3_2__1();

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
    // $ANTLR end "rule__Course__Group_3_2__0"


    // $ANTLR start "rule__Course__Group_3_2__0__Impl"
    // InternalRax2.g:713:1: rule__Course__Group_3_2__0__Impl : ( ',' ) ;
    public final void rule__Course__Group_3_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:717:1: ( ( ',' ) )
            // InternalRax2.g:718:1: ( ',' )
            {
            // InternalRax2.g:718:1: ( ',' )
            // InternalRax2.g:719:2: ','
            {
             before(grammarAccess.getCourseAccess().getCommaKeyword_3_2_0()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getCourseAccess().getCommaKeyword_3_2_0()); 

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
    // $ANTLR end "rule__Course__Group_3_2__0__Impl"


    // $ANTLR start "rule__Course__Group_3_2__1"
    // InternalRax2.g:728:1: rule__Course__Group_3_2__1 : rule__Course__Group_3_2__1__Impl ;
    public final void rule__Course__Group_3_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:732:1: ( rule__Course__Group_3_2__1__Impl )
            // InternalRax2.g:733:2: rule__Course__Group_3_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Course__Group_3_2__1__Impl();

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
    // $ANTLR end "rule__Course__Group_3_2__1"


    // $ANTLR start "rule__Course__Group_3_2__1__Impl"
    // InternalRax2.g:739:1: rule__Course__Group_3_2__1__Impl : ( ( rule__Course__RolesAssignment_3_2_1 ) ) ;
    public final void rule__Course__Group_3_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:743:1: ( ( ( rule__Course__RolesAssignment_3_2_1 ) ) )
            // InternalRax2.g:744:1: ( ( rule__Course__RolesAssignment_3_2_1 ) )
            {
            // InternalRax2.g:744:1: ( ( rule__Course__RolesAssignment_3_2_1 ) )
            // InternalRax2.g:745:2: ( rule__Course__RolesAssignment_3_2_1 )
            {
             before(grammarAccess.getCourseAccess().getRolesAssignment_3_2_1()); 
            // InternalRax2.g:746:2: ( rule__Course__RolesAssignment_3_2_1 )
            // InternalRax2.g:746:3: rule__Course__RolesAssignment_3_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Course__RolesAssignment_3_2_1();

            state._fsp--;


            }

             after(grammarAccess.getCourseAccess().getRolesAssignment_3_2_1()); 

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
    // $ANTLR end "rule__Course__Group_3_2__1__Impl"


    // $ANTLR start "rule__Role__Group__0"
    // InternalRax2.g:755:1: rule__Role__Group__0 : rule__Role__Group__0__Impl rule__Role__Group__1 ;
    public final void rule__Role__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:759:1: ( rule__Role__Group__0__Impl rule__Role__Group__1 )
            // InternalRax2.g:760:2: rule__Role__Group__0__Impl rule__Role__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__Role__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Role__Group__1();

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
    // $ANTLR end "rule__Role__Group__0"


    // $ANTLR start "rule__Role__Group__0__Impl"
    // InternalRax2.g:767:1: rule__Role__Group__0__Impl : ( ( rule__Role__NameAssignment_0 ) ) ;
    public final void rule__Role__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:771:1: ( ( ( rule__Role__NameAssignment_0 ) ) )
            // InternalRax2.g:772:1: ( ( rule__Role__NameAssignment_0 ) )
            {
            // InternalRax2.g:772:1: ( ( rule__Role__NameAssignment_0 ) )
            // InternalRax2.g:773:2: ( rule__Role__NameAssignment_0 )
            {
             before(grammarAccess.getRoleAccess().getNameAssignment_0()); 
            // InternalRax2.g:774:2: ( rule__Role__NameAssignment_0 )
            // InternalRax2.g:774:3: rule__Role__NameAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Role__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getRoleAccess().getNameAssignment_0()); 

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
    // $ANTLR end "rule__Role__Group__0__Impl"


    // $ANTLR start "rule__Role__Group__1"
    // InternalRax2.g:782:1: rule__Role__Group__1 : rule__Role__Group__1__Impl rule__Role__Group__2 ;
    public final void rule__Role__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:786:1: ( rule__Role__Group__1__Impl rule__Role__Group__2 )
            // InternalRax2.g:787:2: rule__Role__Group__1__Impl rule__Role__Group__2
            {
            pushFollow(FOLLOW_16);
            rule__Role__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Role__Group__2();

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
    // $ANTLR end "rule__Role__Group__1"


    // $ANTLR start "rule__Role__Group__1__Impl"
    // InternalRax2.g:794:1: rule__Role__Group__1__Impl : ( 'for' ) ;
    public final void rule__Role__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:798:1: ( ( 'for' ) )
            // InternalRax2.g:799:1: ( 'for' )
            {
            // InternalRax2.g:799:1: ( 'for' )
            // InternalRax2.g:800:2: 'for'
            {
             before(grammarAccess.getRoleAccess().getForKeyword_1()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getRoleAccess().getForKeyword_1()); 

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
    // $ANTLR end "rule__Role__Group__1__Impl"


    // $ANTLR start "rule__Role__Group__2"
    // InternalRax2.g:809:1: rule__Role__Group__2 : rule__Role__Group__2__Impl ;
    public final void rule__Role__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:813:1: ( rule__Role__Group__2__Impl )
            // InternalRax2.g:814:2: rule__Role__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Role__Group__2__Impl();

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
    // $ANTLR end "rule__Role__Group__2"


    // $ANTLR start "rule__Role__Group__2__Impl"
    // InternalRax2.g:820:1: rule__Role__Group__2__Impl : ( ( rule__Role__WorkloadAssignment_2 ) ) ;
    public final void rule__Role__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:824:1: ( ( ( rule__Role__WorkloadAssignment_2 ) ) )
            // InternalRax2.g:825:1: ( ( rule__Role__WorkloadAssignment_2 ) )
            {
            // InternalRax2.g:825:1: ( ( rule__Role__WorkloadAssignment_2 ) )
            // InternalRax2.g:826:2: ( rule__Role__WorkloadAssignment_2 )
            {
             before(grammarAccess.getRoleAccess().getWorkloadAssignment_2()); 
            // InternalRax2.g:827:2: ( rule__Role__WorkloadAssignment_2 )
            // InternalRax2.g:827:3: rule__Role__WorkloadAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Role__WorkloadAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getRoleAccess().getWorkloadAssignment_2()); 

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
    // $ANTLR end "rule__Role__Group__2__Impl"


    // $ANTLR start "rule__FactorAsPercentage__Group__0"
    // InternalRax2.g:836:1: rule__FactorAsPercentage__Group__0 : rule__FactorAsPercentage__Group__0__Impl rule__FactorAsPercentage__Group__1 ;
    public final void rule__FactorAsPercentage__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:840:1: ( rule__FactorAsPercentage__Group__0__Impl rule__FactorAsPercentage__Group__1 )
            // InternalRax2.g:841:2: rule__FactorAsPercentage__Group__0__Impl rule__FactorAsPercentage__Group__1
            {
            pushFollow(FOLLOW_17);
            rule__FactorAsPercentage__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FactorAsPercentage__Group__1();

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
    // $ANTLR end "rule__FactorAsPercentage__Group__0"


    // $ANTLR start "rule__FactorAsPercentage__Group__0__Impl"
    // InternalRax2.g:848:1: rule__FactorAsPercentage__Group__0__Impl : ( RULE_INT ) ;
    public final void rule__FactorAsPercentage__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:852:1: ( ( RULE_INT ) )
            // InternalRax2.g:853:1: ( RULE_INT )
            {
            // InternalRax2.g:853:1: ( RULE_INT )
            // InternalRax2.g:854:2: RULE_INT
            {
             before(grammarAccess.getFactorAsPercentageAccess().getINTTerminalRuleCall_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getFactorAsPercentageAccess().getINTTerminalRuleCall_0()); 

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
    // $ANTLR end "rule__FactorAsPercentage__Group__0__Impl"


    // $ANTLR start "rule__FactorAsPercentage__Group__1"
    // InternalRax2.g:863:1: rule__FactorAsPercentage__Group__1 : rule__FactorAsPercentage__Group__1__Impl rule__FactorAsPercentage__Group__2 ;
    public final void rule__FactorAsPercentage__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:867:1: ( rule__FactorAsPercentage__Group__1__Impl rule__FactorAsPercentage__Group__2 )
            // InternalRax2.g:868:2: rule__FactorAsPercentage__Group__1__Impl rule__FactorAsPercentage__Group__2
            {
            pushFollow(FOLLOW_17);
            rule__FactorAsPercentage__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FactorAsPercentage__Group__2();

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
    // $ANTLR end "rule__FactorAsPercentage__Group__1"


    // $ANTLR start "rule__FactorAsPercentage__Group__1__Impl"
    // InternalRax2.g:875:1: rule__FactorAsPercentage__Group__1__Impl : ( ( rule__FactorAsPercentage__Group_1__0 )? ) ;
    public final void rule__FactorAsPercentage__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:879:1: ( ( ( rule__FactorAsPercentage__Group_1__0 )? ) )
            // InternalRax2.g:880:1: ( ( rule__FactorAsPercentage__Group_1__0 )? )
            {
            // InternalRax2.g:880:1: ( ( rule__FactorAsPercentage__Group_1__0 )? )
            // InternalRax2.g:881:2: ( rule__FactorAsPercentage__Group_1__0 )?
            {
             before(grammarAccess.getFactorAsPercentageAccess().getGroup_1()); 
            // InternalRax2.g:882:2: ( rule__FactorAsPercentage__Group_1__0 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==19) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalRax2.g:882:3: rule__FactorAsPercentage__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__FactorAsPercentage__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getFactorAsPercentageAccess().getGroup_1()); 

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
    // $ANTLR end "rule__FactorAsPercentage__Group__1__Impl"


    // $ANTLR start "rule__FactorAsPercentage__Group__2"
    // InternalRax2.g:890:1: rule__FactorAsPercentage__Group__2 : rule__FactorAsPercentage__Group__2__Impl ;
    public final void rule__FactorAsPercentage__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:894:1: ( rule__FactorAsPercentage__Group__2__Impl )
            // InternalRax2.g:895:2: rule__FactorAsPercentage__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FactorAsPercentage__Group__2__Impl();

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
    // $ANTLR end "rule__FactorAsPercentage__Group__2"


    // $ANTLR start "rule__FactorAsPercentage__Group__2__Impl"
    // InternalRax2.g:901:1: rule__FactorAsPercentage__Group__2__Impl : ( '%' ) ;
    public final void rule__FactorAsPercentage__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:905:1: ( ( '%' ) )
            // InternalRax2.g:906:1: ( '%' )
            {
            // InternalRax2.g:906:1: ( '%' )
            // InternalRax2.g:907:2: '%'
            {
             before(grammarAccess.getFactorAsPercentageAccess().getPercentSignKeyword_2()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getFactorAsPercentageAccess().getPercentSignKeyword_2()); 

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
    // $ANTLR end "rule__FactorAsPercentage__Group__2__Impl"


    // $ANTLR start "rule__FactorAsPercentage__Group_1__0"
    // InternalRax2.g:917:1: rule__FactorAsPercentage__Group_1__0 : rule__FactorAsPercentage__Group_1__0__Impl rule__FactorAsPercentage__Group_1__1 ;
    public final void rule__FactorAsPercentage__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:921:1: ( rule__FactorAsPercentage__Group_1__0__Impl rule__FactorAsPercentage__Group_1__1 )
            // InternalRax2.g:922:2: rule__FactorAsPercentage__Group_1__0__Impl rule__FactorAsPercentage__Group_1__1
            {
            pushFollow(FOLLOW_16);
            rule__FactorAsPercentage__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FactorAsPercentage__Group_1__1();

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
    // $ANTLR end "rule__FactorAsPercentage__Group_1__0"


    // $ANTLR start "rule__FactorAsPercentage__Group_1__0__Impl"
    // InternalRax2.g:929:1: rule__FactorAsPercentage__Group_1__0__Impl : ( '.' ) ;
    public final void rule__FactorAsPercentage__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:933:1: ( ( '.' ) )
            // InternalRax2.g:934:1: ( '.' )
            {
            // InternalRax2.g:934:1: ( '.' )
            // InternalRax2.g:935:2: '.'
            {
             before(grammarAccess.getFactorAsPercentageAccess().getFullStopKeyword_1_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getFactorAsPercentageAccess().getFullStopKeyword_1_0()); 

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
    // $ANTLR end "rule__FactorAsPercentage__Group_1__0__Impl"


    // $ANTLR start "rule__FactorAsPercentage__Group_1__1"
    // InternalRax2.g:944:1: rule__FactorAsPercentage__Group_1__1 : rule__FactorAsPercentage__Group_1__1__Impl ;
    public final void rule__FactorAsPercentage__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:948:1: ( rule__FactorAsPercentage__Group_1__1__Impl )
            // InternalRax2.g:949:2: rule__FactorAsPercentage__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FactorAsPercentage__Group_1__1__Impl();

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
    // $ANTLR end "rule__FactorAsPercentage__Group_1__1"


    // $ANTLR start "rule__FactorAsPercentage__Group_1__1__Impl"
    // InternalRax2.g:955:1: rule__FactorAsPercentage__Group_1__1__Impl : ( RULE_INT ) ;
    public final void rule__FactorAsPercentage__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:959:1: ( ( RULE_INT ) )
            // InternalRax2.g:960:1: ( RULE_INT )
            {
            // InternalRax2.g:960:1: ( RULE_INT )
            // InternalRax2.g:961:2: RULE_INT
            {
             before(grammarAccess.getFactorAsPercentageAccess().getINTTerminalRuleCall_1_1()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getFactorAsPercentageAccess().getINTTerminalRuleCall_1_1()); 

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
    // $ANTLR end "rule__FactorAsPercentage__Group_1__1__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group__0"
    // InternalRax2.g:971:1: rule__ResourceAllocation__Group__0 : rule__ResourceAllocation__Group__0__Impl rule__ResourceAllocation__Group__1 ;
    public final void rule__ResourceAllocation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:975:1: ( rule__ResourceAllocation__Group__0__Impl rule__ResourceAllocation__Group__1 )
            // InternalRax2.g:976:2: rule__ResourceAllocation__Group__0__Impl rule__ResourceAllocation__Group__1
            {
            pushFollow(FOLLOW_18);
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
    // InternalRax2.g:983:1: rule__ResourceAllocation__Group__0__Impl : ( ( rule__ResourceAllocation__PersonAssignment_0 ) ) ;
    public final void rule__ResourceAllocation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:987:1: ( ( ( rule__ResourceAllocation__PersonAssignment_0 ) ) )
            // InternalRax2.g:988:1: ( ( rule__ResourceAllocation__PersonAssignment_0 ) )
            {
            // InternalRax2.g:988:1: ( ( rule__ResourceAllocation__PersonAssignment_0 ) )
            // InternalRax2.g:989:2: ( rule__ResourceAllocation__PersonAssignment_0 )
            {
             before(grammarAccess.getResourceAllocationAccess().getPersonAssignment_0()); 
            // InternalRax2.g:990:2: ( rule__ResourceAllocation__PersonAssignment_0 )
            // InternalRax2.g:990:3: rule__ResourceAllocation__PersonAssignment_0
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
    // InternalRax2.g:998:1: rule__ResourceAllocation__Group__1 : rule__ResourceAllocation__Group__1__Impl rule__ResourceAllocation__Group__2 ;
    public final void rule__ResourceAllocation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1002:1: ( rule__ResourceAllocation__Group__1__Impl rule__ResourceAllocation__Group__2 )
            // InternalRax2.g:1003:2: rule__ResourceAllocation__Group__1__Impl rule__ResourceAllocation__Group__2
            {
            pushFollow(FOLLOW_19);
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
    // InternalRax2.g:1010:1: rule__ResourceAllocation__Group__1__Impl : ( 'is' ) ;
    public final void rule__ResourceAllocation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1014:1: ( ( 'is' ) )
            // InternalRax2.g:1015:1: ( 'is' )
            {
            // InternalRax2.g:1015:1: ( 'is' )
            // InternalRax2.g:1016:2: 'is'
            {
             before(grammarAccess.getResourceAllocationAccess().getIsKeyword_1()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getIsKeyword_1()); 

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
    // InternalRax2.g:1025:1: rule__ResourceAllocation__Group__2 : rule__ResourceAllocation__Group__2__Impl ;
    public final void rule__ResourceAllocation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1029:1: ( rule__ResourceAllocation__Group__2__Impl )
            // InternalRax2.g:1030:2: rule__ResourceAllocation__Group__2__Impl
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
    // InternalRax2.g:1036:1: rule__ResourceAllocation__Group__2__Impl : ( ( rule__ResourceAllocation__Alternatives_2 ) ) ;
    public final void rule__ResourceAllocation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1040:1: ( ( ( rule__ResourceAllocation__Alternatives_2 ) ) )
            // InternalRax2.g:1041:1: ( ( rule__ResourceAllocation__Alternatives_2 ) )
            {
            // InternalRax2.g:1041:1: ( ( rule__ResourceAllocation__Alternatives_2 ) )
            // InternalRax2.g:1042:2: ( rule__ResourceAllocation__Alternatives_2 )
            {
             before(grammarAccess.getResourceAllocationAccess().getAlternatives_2()); 
            // InternalRax2.g:1043:2: ( rule__ResourceAllocation__Alternatives_2 )
            // InternalRax2.g:1043:3: rule__ResourceAllocation__Alternatives_2
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Alternatives_2();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getAlternatives_2()); 

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


    // $ANTLR start "rule__ResourceAllocation__Group_2_0__0"
    // InternalRax2.g:1052:1: rule__ResourceAllocation__Group_2_0__0 : rule__ResourceAllocation__Group_2_0__0__Impl rule__ResourceAllocation__Group_2_0__1 ;
    public final void rule__ResourceAllocation__Group_2_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1056:1: ( rule__ResourceAllocation__Group_2_0__0__Impl rule__ResourceAllocation__Group_2_0__1 )
            // InternalRax2.g:1057:2: rule__ResourceAllocation__Group_2_0__0__Impl rule__ResourceAllocation__Group_2_0__1
            {
            pushFollow(FOLLOW_20);
            rule__ResourceAllocation__Group_2_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group_2_0__1();

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_0__0"


    // $ANTLR start "rule__ResourceAllocation__Group_2_0__0__Impl"
    // InternalRax2.g:1064:1: rule__ResourceAllocation__Group_2_0__0__Impl : ( ( rule__ResourceAllocation__FactorAssignment_2_0_0 ) ) ;
    public final void rule__ResourceAllocation__Group_2_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1068:1: ( ( ( rule__ResourceAllocation__FactorAssignment_2_0_0 ) ) )
            // InternalRax2.g:1069:1: ( ( rule__ResourceAllocation__FactorAssignment_2_0_0 ) )
            {
            // InternalRax2.g:1069:1: ( ( rule__ResourceAllocation__FactorAssignment_2_0_0 ) )
            // InternalRax2.g:1070:2: ( rule__ResourceAllocation__FactorAssignment_2_0_0 )
            {
             before(grammarAccess.getResourceAllocationAccess().getFactorAssignment_2_0_0()); 
            // InternalRax2.g:1071:2: ( rule__ResourceAllocation__FactorAssignment_2_0_0 )
            // InternalRax2.g:1071:3: rule__ResourceAllocation__FactorAssignment_2_0_0
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__FactorAssignment_2_0_0();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getFactorAssignment_2_0_0()); 

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_0__0__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group_2_0__1"
    // InternalRax2.g:1079:1: rule__ResourceAllocation__Group_2_0__1 : rule__ResourceAllocation__Group_2_0__1__Impl rule__ResourceAllocation__Group_2_0__2 ;
    public final void rule__ResourceAllocation__Group_2_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1083:1: ( rule__ResourceAllocation__Group_2_0__1__Impl rule__ResourceAllocation__Group_2_0__2 )
            // InternalRax2.g:1084:2: rule__ResourceAllocation__Group_2_0__1__Impl rule__ResourceAllocation__Group_2_0__2
            {
            pushFollow(FOLLOW_12);
            rule__ResourceAllocation__Group_2_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group_2_0__2();

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_0__1"


    // $ANTLR start "rule__ResourceAllocation__Group_2_0__1__Impl"
    // InternalRax2.g:1091:1: rule__ResourceAllocation__Group_2_0__1__Impl : ( 'in' ) ;
    public final void rule__ResourceAllocation__Group_2_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1095:1: ( ( 'in' ) )
            // InternalRax2.g:1096:1: ( 'in' )
            {
            // InternalRax2.g:1096:1: ( 'in' )
            // InternalRax2.g:1097:2: 'in'
            {
             before(grammarAccess.getResourceAllocationAccess().getInKeyword_2_0_1()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getInKeyword_2_0_1()); 

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_0__1__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group_2_0__2"
    // InternalRax2.g:1106:1: rule__ResourceAllocation__Group_2_0__2 : rule__ResourceAllocation__Group_2_0__2__Impl ;
    public final void rule__ResourceAllocation__Group_2_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1110:1: ( rule__ResourceAllocation__Group_2_0__2__Impl )
            // InternalRax2.g:1111:2: rule__ResourceAllocation__Group_2_0__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group_2_0__2__Impl();

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_0__2"


    // $ANTLR start "rule__ResourceAllocation__Group_2_0__2__Impl"
    // InternalRax2.g:1117:1: rule__ResourceAllocation__Group_2_0__2__Impl : ( ( rule__ResourceAllocation__CourseAssignment_2_0_2 ) ) ;
    public final void rule__ResourceAllocation__Group_2_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1121:1: ( ( ( rule__ResourceAllocation__CourseAssignment_2_0_2 ) ) )
            // InternalRax2.g:1122:1: ( ( rule__ResourceAllocation__CourseAssignment_2_0_2 ) )
            {
            // InternalRax2.g:1122:1: ( ( rule__ResourceAllocation__CourseAssignment_2_0_2 ) )
            // InternalRax2.g:1123:2: ( rule__ResourceAllocation__CourseAssignment_2_0_2 )
            {
             before(grammarAccess.getResourceAllocationAccess().getCourseAssignment_2_0_2()); 
            // InternalRax2.g:1124:2: ( rule__ResourceAllocation__CourseAssignment_2_0_2 )
            // InternalRax2.g:1124:3: rule__ResourceAllocation__CourseAssignment_2_0_2
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__CourseAssignment_2_0_2();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getCourseAssignment_2_0_2()); 

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_0__2__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__0"
    // InternalRax2.g:1133:1: rule__ResourceAllocation__Group_2_1__0 : rule__ResourceAllocation__Group_2_1__0__Impl rule__ResourceAllocation__Group_2_1__1 ;
    public final void rule__ResourceAllocation__Group_2_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1137:1: ( rule__ResourceAllocation__Group_2_1__0__Impl rule__ResourceAllocation__Group_2_1__1 )
            // InternalRax2.g:1138:2: rule__ResourceAllocation__Group_2_1__0__Impl rule__ResourceAllocation__Group_2_1__1
            {
            pushFollow(FOLLOW_12);
            rule__ResourceAllocation__Group_2_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group_2_1__1();

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__0"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__0__Impl"
    // InternalRax2.g:1145:1: rule__ResourceAllocation__Group_2_1__0__Impl : ( 'in' ) ;
    public final void rule__ResourceAllocation__Group_2_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1149:1: ( ( 'in' ) )
            // InternalRax2.g:1150:1: ( 'in' )
            {
            // InternalRax2.g:1150:1: ( 'in' )
            // InternalRax2.g:1151:2: 'in'
            {
             before(grammarAccess.getResourceAllocationAccess().getInKeyword_2_1_0()); 
            match(input,21,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getInKeyword_2_1_0()); 

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__0__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__1"
    // InternalRax2.g:1160:1: rule__ResourceAllocation__Group_2_1__1 : rule__ResourceAllocation__Group_2_1__1__Impl rule__ResourceAllocation__Group_2_1__2 ;
    public final void rule__ResourceAllocation__Group_2_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1164:1: ( rule__ResourceAllocation__Group_2_1__1__Impl rule__ResourceAllocation__Group_2_1__2 )
            // InternalRax2.g:1165:2: rule__ResourceAllocation__Group_2_1__1__Impl rule__ResourceAllocation__Group_2_1__2
            {
            pushFollow(FOLLOW_21);
            rule__ResourceAllocation__Group_2_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group_2_1__2();

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__1"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__1__Impl"
    // InternalRax2.g:1172:1: rule__ResourceAllocation__Group_2_1__1__Impl : ( ( rule__ResourceAllocation__CourseAssignment_2_1_1 ) ) ;
    public final void rule__ResourceAllocation__Group_2_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1176:1: ( ( ( rule__ResourceAllocation__CourseAssignment_2_1_1 ) ) )
            // InternalRax2.g:1177:1: ( ( rule__ResourceAllocation__CourseAssignment_2_1_1 ) )
            {
            // InternalRax2.g:1177:1: ( ( rule__ResourceAllocation__CourseAssignment_2_1_1 ) )
            // InternalRax2.g:1178:2: ( rule__ResourceAllocation__CourseAssignment_2_1_1 )
            {
             before(grammarAccess.getResourceAllocationAccess().getCourseAssignment_2_1_1()); 
            // InternalRax2.g:1179:2: ( rule__ResourceAllocation__CourseAssignment_2_1_1 )
            // InternalRax2.g:1179:3: rule__ResourceAllocation__CourseAssignment_2_1_1
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__CourseAssignment_2_1_1();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getCourseAssignment_2_1_1()); 

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__1__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__2"
    // InternalRax2.g:1187:1: rule__ResourceAllocation__Group_2_1__2 : rule__ResourceAllocation__Group_2_1__2__Impl rule__ResourceAllocation__Group_2_1__3 ;
    public final void rule__ResourceAllocation__Group_2_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1191:1: ( rule__ResourceAllocation__Group_2_1__2__Impl rule__ResourceAllocation__Group_2_1__3 )
            // InternalRax2.g:1192:2: rule__ResourceAllocation__Group_2_1__2__Impl rule__ResourceAllocation__Group_2_1__3
            {
            pushFollow(FOLLOW_16);
            rule__ResourceAllocation__Group_2_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group_2_1__3();

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__2"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__2__Impl"
    // InternalRax2.g:1199:1: rule__ResourceAllocation__Group_2_1__2__Impl : ( 'as' ) ;
    public final void rule__ResourceAllocation__Group_2_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1203:1: ( ( 'as' ) )
            // InternalRax2.g:1204:1: ( 'as' )
            {
            // InternalRax2.g:1204:1: ( 'as' )
            // InternalRax2.g:1205:2: 'as'
            {
             before(grammarAccess.getResourceAllocationAccess().getAsKeyword_2_1_2()); 
            match(input,22,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getAsKeyword_2_1_2()); 

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__2__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__3"
    // InternalRax2.g:1214:1: rule__ResourceAllocation__Group_2_1__3 : rule__ResourceAllocation__Group_2_1__3__Impl rule__ResourceAllocation__Group_2_1__4 ;
    public final void rule__ResourceAllocation__Group_2_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1218:1: ( rule__ResourceAllocation__Group_2_1__3__Impl rule__ResourceAllocation__Group_2_1__4 )
            // InternalRax2.g:1219:2: rule__ResourceAllocation__Group_2_1__3__Impl rule__ResourceAllocation__Group_2_1__4
            {
            pushFollow(FOLLOW_12);
            rule__ResourceAllocation__Group_2_1__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group_2_1__4();

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__3"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__3__Impl"
    // InternalRax2.g:1226:1: rule__ResourceAllocation__Group_2_1__3__Impl : ( ( rule__ResourceAllocation__FactorAssignment_2_1_3 ) ) ;
    public final void rule__ResourceAllocation__Group_2_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1230:1: ( ( ( rule__ResourceAllocation__FactorAssignment_2_1_3 ) ) )
            // InternalRax2.g:1231:1: ( ( rule__ResourceAllocation__FactorAssignment_2_1_3 ) )
            {
            // InternalRax2.g:1231:1: ( ( rule__ResourceAllocation__FactorAssignment_2_1_3 ) )
            // InternalRax2.g:1232:2: ( rule__ResourceAllocation__FactorAssignment_2_1_3 )
            {
             before(grammarAccess.getResourceAllocationAccess().getFactorAssignment_2_1_3()); 
            // InternalRax2.g:1233:2: ( rule__ResourceAllocation__FactorAssignment_2_1_3 )
            // InternalRax2.g:1233:3: rule__ResourceAllocation__FactorAssignment_2_1_3
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__FactorAssignment_2_1_3();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getFactorAssignment_2_1_3()); 

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__3__Impl"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__4"
    // InternalRax2.g:1241:1: rule__ResourceAllocation__Group_2_1__4 : rule__ResourceAllocation__Group_2_1__4__Impl ;
    public final void rule__ResourceAllocation__Group_2_1__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1245:1: ( rule__ResourceAllocation__Group_2_1__4__Impl )
            // InternalRax2.g:1246:2: rule__ResourceAllocation__Group_2_1__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__Group_2_1__4__Impl();

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__4"


    // $ANTLR start "rule__ResourceAllocation__Group_2_1__4__Impl"
    // InternalRax2.g:1252:1: rule__ResourceAllocation__Group_2_1__4__Impl : ( ( rule__ResourceAllocation__RoleAssignment_2_1_4 ) ) ;
    public final void rule__ResourceAllocation__Group_2_1__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1256:1: ( ( ( rule__ResourceAllocation__RoleAssignment_2_1_4 ) ) )
            // InternalRax2.g:1257:1: ( ( rule__ResourceAllocation__RoleAssignment_2_1_4 ) )
            {
            // InternalRax2.g:1257:1: ( ( rule__ResourceAllocation__RoleAssignment_2_1_4 ) )
            // InternalRax2.g:1258:2: ( rule__ResourceAllocation__RoleAssignment_2_1_4 )
            {
             before(grammarAccess.getResourceAllocationAccess().getRoleAssignment_2_1_4()); 
            // InternalRax2.g:1259:2: ( rule__ResourceAllocation__RoleAssignment_2_1_4 )
            // InternalRax2.g:1259:3: rule__ResourceAllocation__RoleAssignment_2_1_4
            {
            pushFollow(FOLLOW_2);
            rule__ResourceAllocation__RoleAssignment_2_1_4();

            state._fsp--;


            }

             after(grammarAccess.getResourceAllocationAccess().getRoleAssignment_2_1_4()); 

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
    // $ANTLR end "rule__ResourceAllocation__Group_2_1__4__Impl"


    // $ANTLR start "rule__Department__ShortNameAssignment_0"
    // InternalRax2.g:1268:1: rule__Department__ShortNameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Department__ShortNameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1272:1: ( ( RULE_ID ) )
            // InternalRax2.g:1273:2: ( RULE_ID )
            {
            // InternalRax2.g:1273:2: ( RULE_ID )
            // InternalRax2.g:1274:3: RULE_ID
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
    // InternalRax2.g:1283:1: rule__Department__NameAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Department__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1287:1: ( ( RULE_STRING ) )
            // InternalRax2.g:1288:2: ( RULE_STRING )
            {
            // InternalRax2.g:1288:2: ( RULE_STRING )
            // InternalRax2.g:1289:3: RULE_STRING
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


    // $ANTLR start "rule__Department__StaffAssignment_4"
    // InternalRax2.g:1298:1: rule__Department__StaffAssignment_4 : ( rulePerson ) ;
    public final void rule__Department__StaffAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1302:1: ( ( rulePerson ) )
            // InternalRax2.g:1303:2: ( rulePerson )
            {
            // InternalRax2.g:1303:2: ( rulePerson )
            // InternalRax2.g:1304:3: rulePerson
            {
             before(grammarAccess.getDepartmentAccess().getStaffPersonParserRuleCall_4_0()); 
            pushFollow(FOLLOW_2);
            rulePerson();

            state._fsp--;

             after(grammarAccess.getDepartmentAccess().getStaffPersonParserRuleCall_4_0()); 

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
    // $ANTLR end "rule__Department__StaffAssignment_4"


    // $ANTLR start "rule__Department__CoursesAssignment_6"
    // InternalRax2.g:1313:1: rule__Department__CoursesAssignment_6 : ( ruleCourse ) ;
    public final void rule__Department__CoursesAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1317:1: ( ( ruleCourse ) )
            // InternalRax2.g:1318:2: ( ruleCourse )
            {
            // InternalRax2.g:1318:2: ( ruleCourse )
            // InternalRax2.g:1319:3: ruleCourse
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


    // $ANTLR start "rule__Department__ResourceAllocationsAssignment_8"
    // InternalRax2.g:1328:1: rule__Department__ResourceAllocationsAssignment_8 : ( ruleResourceAllocation ) ;
    public final void rule__Department__ResourceAllocationsAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1332:1: ( ( ruleResourceAllocation ) )
            // InternalRax2.g:1333:2: ( ruleResourceAllocation )
            {
            // InternalRax2.g:1333:2: ( ruleResourceAllocation )
            // InternalRax2.g:1334:3: ruleResourceAllocation
            {
             before(grammarAccess.getDepartmentAccess().getResourceAllocationsResourceAllocationParserRuleCall_8_0()); 
            pushFollow(FOLLOW_2);
            ruleResourceAllocation();

            state._fsp--;

             after(grammarAccess.getDepartmentAccess().getResourceAllocationsResourceAllocationParserRuleCall_8_0()); 

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
    // $ANTLR end "rule__Department__ResourceAllocationsAssignment_8"


    // $ANTLR start "rule__Person__NameAssignment"
    // InternalRax2.g:1343:1: rule__Person__NameAssignment : ( ruleNameOrString ) ;
    public final void rule__Person__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1347:1: ( ( ruleNameOrString ) )
            // InternalRax2.g:1348:2: ( ruleNameOrString )
            {
            // InternalRax2.g:1348:2: ( ruleNameOrString )
            // InternalRax2.g:1349:3: ruleNameOrString
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
    // InternalRax2.g:1358:1: rule__Course__CodeAssignment_0 : ( RULE_ID ) ;
    public final void rule__Course__CodeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1362:1: ( ( RULE_ID ) )
            // InternalRax2.g:1363:2: ( RULE_ID )
            {
            // InternalRax2.g:1363:2: ( RULE_ID )
            // InternalRax2.g:1364:3: RULE_ID
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
    // InternalRax2.g:1373:1: rule__Course__NameAssignment_2 : ( ruleNameOrString ) ;
    public final void rule__Course__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1377:1: ( ( ruleNameOrString ) )
            // InternalRax2.g:1378:2: ( ruleNameOrString )
            {
            // InternalRax2.g:1378:2: ( ruleNameOrString )
            // InternalRax2.g:1379:3: ruleNameOrString
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


    // $ANTLR start "rule__Course__RolesAssignment_3_1"
    // InternalRax2.g:1388:1: rule__Course__RolesAssignment_3_1 : ( ruleRole ) ;
    public final void rule__Course__RolesAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1392:1: ( ( ruleRole ) )
            // InternalRax2.g:1393:2: ( ruleRole )
            {
            // InternalRax2.g:1393:2: ( ruleRole )
            // InternalRax2.g:1394:3: ruleRole
            {
             before(grammarAccess.getCourseAccess().getRolesRoleParserRuleCall_3_1_0()); 
            pushFollow(FOLLOW_2);
            ruleRole();

            state._fsp--;

             after(grammarAccess.getCourseAccess().getRolesRoleParserRuleCall_3_1_0()); 

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
    // $ANTLR end "rule__Course__RolesAssignment_3_1"


    // $ANTLR start "rule__Course__RolesAssignment_3_2_1"
    // InternalRax2.g:1403:1: rule__Course__RolesAssignment_3_2_1 : ( ruleRole ) ;
    public final void rule__Course__RolesAssignment_3_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1407:1: ( ( ruleRole ) )
            // InternalRax2.g:1408:2: ( ruleRole )
            {
            // InternalRax2.g:1408:2: ( ruleRole )
            // InternalRax2.g:1409:3: ruleRole
            {
             before(grammarAccess.getCourseAccess().getRolesRoleParserRuleCall_3_2_1_0()); 
            pushFollow(FOLLOW_2);
            ruleRole();

            state._fsp--;

             after(grammarAccess.getCourseAccess().getRolesRoleParserRuleCall_3_2_1_0()); 

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
    // $ANTLR end "rule__Course__RolesAssignment_3_2_1"


    // $ANTLR start "rule__Role__NameAssignment_0"
    // InternalRax2.g:1418:1: rule__Role__NameAssignment_0 : ( RULE_ID ) ;
    public final void rule__Role__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1422:1: ( ( RULE_ID ) )
            // InternalRax2.g:1423:2: ( RULE_ID )
            {
            // InternalRax2.g:1423:2: ( RULE_ID )
            // InternalRax2.g:1424:3: RULE_ID
            {
             before(grammarAccess.getRoleAccess().getNameIDTerminalRuleCall_0_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getRoleAccess().getNameIDTerminalRuleCall_0_0()); 

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
    // $ANTLR end "rule__Role__NameAssignment_0"


    // $ANTLR start "rule__Role__WorkloadAssignment_2"
    // InternalRax2.g:1433:1: rule__Role__WorkloadAssignment_2 : ( ruleFactorAsPercentage ) ;
    public final void rule__Role__WorkloadAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1437:1: ( ( ruleFactorAsPercentage ) )
            // InternalRax2.g:1438:2: ( ruleFactorAsPercentage )
            {
            // InternalRax2.g:1438:2: ( ruleFactorAsPercentage )
            // InternalRax2.g:1439:3: ruleFactorAsPercentage
            {
             before(grammarAccess.getRoleAccess().getWorkloadFactorAsPercentageParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleFactorAsPercentage();

            state._fsp--;

             after(grammarAccess.getRoleAccess().getWorkloadFactorAsPercentageParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__Role__WorkloadAssignment_2"


    // $ANTLR start "rule__ResourceAllocation__PersonAssignment_0"
    // InternalRax2.g:1448:1: rule__ResourceAllocation__PersonAssignment_0 : ( ( ruleNameOrString ) ) ;
    public final void rule__ResourceAllocation__PersonAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1452:1: ( ( ( ruleNameOrString ) ) )
            // InternalRax2.g:1453:2: ( ( ruleNameOrString ) )
            {
            // InternalRax2.g:1453:2: ( ( ruleNameOrString ) )
            // InternalRax2.g:1454:3: ( ruleNameOrString )
            {
             before(grammarAccess.getResourceAllocationAccess().getPersonPersonCrossReference_0_0()); 
            // InternalRax2.g:1455:3: ( ruleNameOrString )
            // InternalRax2.g:1456:4: ruleNameOrString
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


    // $ANTLR start "rule__ResourceAllocation__FactorAssignment_2_0_0"
    // InternalRax2.g:1467:1: rule__ResourceAllocation__FactorAssignment_2_0_0 : ( ruleFactorAsPercentage ) ;
    public final void rule__ResourceAllocation__FactorAssignment_2_0_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1471:1: ( ( ruleFactorAsPercentage ) )
            // InternalRax2.g:1472:2: ( ruleFactorAsPercentage )
            {
            // InternalRax2.g:1472:2: ( ruleFactorAsPercentage )
            // InternalRax2.g:1473:3: ruleFactorAsPercentage
            {
             before(grammarAccess.getResourceAllocationAccess().getFactorFactorAsPercentageParserRuleCall_2_0_0_0()); 
            pushFollow(FOLLOW_2);
            ruleFactorAsPercentage();

            state._fsp--;

             after(grammarAccess.getResourceAllocationAccess().getFactorFactorAsPercentageParserRuleCall_2_0_0_0()); 

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
    // $ANTLR end "rule__ResourceAllocation__FactorAssignment_2_0_0"


    // $ANTLR start "rule__ResourceAllocation__CourseAssignment_2_0_2"
    // InternalRax2.g:1482:1: rule__ResourceAllocation__CourseAssignment_2_0_2 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceAllocation__CourseAssignment_2_0_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1486:1: ( ( ( RULE_ID ) ) )
            // InternalRax2.g:1487:2: ( ( RULE_ID ) )
            {
            // InternalRax2.g:1487:2: ( ( RULE_ID ) )
            // InternalRax2.g:1488:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceAllocationAccess().getCourseCourseCrossReference_2_0_2_0()); 
            // InternalRax2.g:1489:3: ( RULE_ID )
            // InternalRax2.g:1490:4: RULE_ID
            {
             before(grammarAccess.getResourceAllocationAccess().getCourseCourseIDTerminalRuleCall_2_0_2_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getCourseCourseIDTerminalRuleCall_2_0_2_0_1()); 

            }

             after(grammarAccess.getResourceAllocationAccess().getCourseCourseCrossReference_2_0_2_0()); 

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
    // $ANTLR end "rule__ResourceAllocation__CourseAssignment_2_0_2"


    // $ANTLR start "rule__ResourceAllocation__CourseAssignment_2_1_1"
    // InternalRax2.g:1501:1: rule__ResourceAllocation__CourseAssignment_2_1_1 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceAllocation__CourseAssignment_2_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1505:1: ( ( ( RULE_ID ) ) )
            // InternalRax2.g:1506:2: ( ( RULE_ID ) )
            {
            // InternalRax2.g:1506:2: ( ( RULE_ID ) )
            // InternalRax2.g:1507:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceAllocationAccess().getCourseCourseCrossReference_2_1_1_0()); 
            // InternalRax2.g:1508:3: ( RULE_ID )
            // InternalRax2.g:1509:4: RULE_ID
            {
             before(grammarAccess.getResourceAllocationAccess().getCourseCourseIDTerminalRuleCall_2_1_1_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getCourseCourseIDTerminalRuleCall_2_1_1_0_1()); 

            }

             after(grammarAccess.getResourceAllocationAccess().getCourseCourseCrossReference_2_1_1_0()); 

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
    // $ANTLR end "rule__ResourceAllocation__CourseAssignment_2_1_1"


    // $ANTLR start "rule__ResourceAllocation__FactorAssignment_2_1_3"
    // InternalRax2.g:1520:1: rule__ResourceAllocation__FactorAssignment_2_1_3 : ( ruleFactorAsPercentage ) ;
    public final void rule__ResourceAllocation__FactorAssignment_2_1_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1524:1: ( ( ruleFactorAsPercentage ) )
            // InternalRax2.g:1525:2: ( ruleFactorAsPercentage )
            {
            // InternalRax2.g:1525:2: ( ruleFactorAsPercentage )
            // InternalRax2.g:1526:3: ruleFactorAsPercentage
            {
             before(grammarAccess.getResourceAllocationAccess().getFactorFactorAsPercentageParserRuleCall_2_1_3_0()); 
            pushFollow(FOLLOW_2);
            ruleFactorAsPercentage();

            state._fsp--;

             after(grammarAccess.getResourceAllocationAccess().getFactorFactorAsPercentageParserRuleCall_2_1_3_0()); 

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
    // $ANTLR end "rule__ResourceAllocation__FactorAssignment_2_1_3"


    // $ANTLR start "rule__ResourceAllocation__RoleAssignment_2_1_4"
    // InternalRax2.g:1535:1: rule__ResourceAllocation__RoleAssignment_2_1_4 : ( ( RULE_ID ) ) ;
    public final void rule__ResourceAllocation__RoleAssignment_2_1_4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalRax2.g:1539:1: ( ( ( RULE_ID ) ) )
            // InternalRax2.g:1540:2: ( ( RULE_ID ) )
            {
            // InternalRax2.g:1540:2: ( ( RULE_ID ) )
            // InternalRax2.g:1541:3: ( RULE_ID )
            {
             before(grammarAccess.getResourceAllocationAccess().getRoleRoleCrossReference_2_1_4_0()); 
            // InternalRax2.g:1542:3: ( RULE_ID )
            // InternalRax2.g:1543:4: RULE_ID
            {
             before(grammarAccess.getResourceAllocationAccess().getRoleRoleIDTerminalRuleCall_2_1_4_0_1()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getResourceAllocationAccess().getRoleRoleIDTerminalRuleCall_2_1_4_0_1()); 

            }

             after(grammarAccess.getResourceAllocationAccess().getRoleRoleCrossReference_2_1_4_0()); 

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
    // $ANTLR end "rule__ResourceAllocation__RoleAssignment_2_1_4"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000002030L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000200040L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000400000L});

}