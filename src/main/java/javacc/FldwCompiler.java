/* FldwCompiler.java */
/* Generated By:JavaCC: Do not edit this line. FldwCompiler.java */
package javacc;

import java.io.*;
import java.lang.reflect.*;
import core.*;import examples.Examples;

public class FldwCompiler implements FldwCompilerConstants {
    public static void main(String[] args) {
//        String[] tests={"a<1.2"};
//        for (String arg : tests) {
            try {
//                            Class.forName("std.Std");
                parse(Examples.BlockExample4);
//                System.out.println(evaluate(arg));
//                return(evaluate(arg));
            } catch (ParseException ex) {
                System.err.println(ex.getMessage());
            }
//        }
    }

    public static void parse(String src) throws ParseException {
        Reader reader = new StringReader(src);
//        Object ret = new FldwCompiler(reader).expr_data();
//        System.out.println(ret);
        new FldwCompiler(reader).program();
    }

/*****************************************************************/
/***************************语法分析*******************************/
/*****************************************************************/
  final public TerminalData terminal_data() throws ParseException {Datable.DataType type;
    Token x;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT_VALUE:{
      x = jj_consume_token(INT_VALUE);
type = Datable.DataType.Int;
      break;
      }
    case DOUBLE_VALUE:{
      x = jj_consume_token(DOUBLE_VALUE);
type = Datable.DataType.Double;
      break;
      }
    case BOOL_VALUE:{
      x = jj_consume_token(BOOL_VALUE);
type = Datable.DataType.Bool;
      break;
      }
    case STRING_VALUE:{
      x = jj_consume_token(STRING_VALUE);
type = Datable.DataType.String;
      break;
      }
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return new TerminalData(type, x.image);}
    throw new Error("Missing return statement in function");
}

  final public SymbolData symbol_data() throws ParseException {Token symbol;
    // 语法：symbol_data() = < SYMBOL>
        symbol = jj_consume_token(SYMBOL);
{if ("" != null) return SymbolTable.CurrentSymbolTable().PutOrGetSymbol(symbol.image, SymbolTable.SymbolType.Data).assertGetSymbolData();}
    throw new Error("Missing return statement in function");
}

// TODO：tmp
  final public ExprData expr_data() throws ParseException {ExprData exprData = new ExprData();
    Datable leftData, rightData;
    leftData = data();
exprData.setLeftData(leftData);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LEFT:{
      jj_consume_token(LEFT);
exprData.setOp(ExprData.ExprOp.LeftOp);
      break;
      }
    case RIGHT:{
      jj_consume_token(RIGHT);
exprData.setOp(ExprData.ExprOp.RightOp);
      break;
      }
    case LEFT_EQUAL:{
      jj_consume_token(LEFT_EQUAL);
      break;
      }
    case RIGHT_EQUAL:{
      jj_consume_token(RIGHT_EQUAL);
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    rightData = data();
exprData.setRightData(rightData);
{if ("" != null) return exprData;}
    throw new Error("Missing return statement in function");
}

  final public Datable data() throws ParseException {Datable data;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT_VALUE:
    case DOUBLE_VALUE:
    case BOOL_VALUE:
    case STRING_VALUE:{
      data = terminal_data();
      break;
      }
    case SYMBOL:{
      data = symbol_data();
      break;
      }
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
{if ("" != null) return data;}
    throw new Error("Missing return statement in function");
}

  final public ListFlow list_flow() throws ParseException {ListFlow listFlow = new ListFlow();
    Datable data;
    jj_consume_token(LSBR);
    data = data();
listFlow.Push(data);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case COMMA:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_1;
      }
      jj_consume_token(COMMA);
      data = data();
listFlow.Push(data);
    }
    jj_consume_token(RSBR);
//        System.out.println(listFlow);
        {if ("" != null) return listFlow;}
    throw new Error("Missing return statement in function");
}

//SymbolFlow symbol_flow() :
//{
//}
//{
//    // 语法：symbol_flow() = < ITER> <SYMBOL> stmt()
//    {
////        System.out.println(new SymbolFlow());
//        return new SymbolDataFlow();
//    }
//}
  final public 
IfElseFlow if_else_flow() throws ParseException {ExprData conditionData;
    Flowable trueFlow;
    Flowable falseFlow = null;
    jj_consume_token(IF);
    jj_consume_token(LBR);
    conditionData = expr_data();
    jj_consume_token(RBR);
    trueFlow = block_flow();
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case ELSE:{
      jj_consume_token(ELSE);
      falseFlow = block_flow();
      break;
      }
    default:
      jj_la1[4] = jj_gen;
      ;
    }
{if ("" != null) return new IfElseFlow(conditionData, trueFlow, falseFlow);}
    throw new Error("Missing return statement in function");
}

  final public WhileFlow while_flow() throws ParseException {ExprData conditionData;
    Flowable trueFlow;
    jj_consume_token(WHILE);
    jj_consume_token(LBR);
    conditionData = expr_data();
    jj_consume_token(RBR);
    trueFlow = block_flow();
{if ("" != null) return new WhileFlow(conditionData, trueFlow);}
    throw new Error("Missing return statement in function");
}

  final public Flowable func_flow() throws ParseException {Token func_symbol;
    func_symbol = jj_consume_token(SYMBOL);
    jj_consume_token(LBR);
    jj_consume_token(RBR);
{if ("" != null) return SymbolTable.CurrentSymbolTable().GetSymbol(func_symbol.image).assertGetFlowable();}
    throw new Error("Missing return statement in function");
}

  final public HeadTailFlow head_tail_flow() throws ParseException {SymbolData headData;
    Token tailListFlowSymbol;
    Flowable tailListFlow;
    HeadTailFlow headTailFlow;
    jj_consume_token(LSBR);
    headData = symbol_data();
    jj_consume_token(SEMIC);
    tailListFlowSymbol = jj_consume_token(SYMBOL);
tailListFlow = SymbolTable.CurrentSymbolTable().PutOrGetSymbol(tailListFlowSymbol.image, SymbolTable.SymbolType.Flow).assertGetFlowable();
    jj_consume_token(RSBR);
{if ("" != null) return new HeadTailFlow(headData, tailListFlow);}
    throw new Error("Missing return statement in function");
}

  final public BlockFlow block_flow() throws ParseException {BlockFlow blockFlow;
    Flowable topFlow;
    jj_consume_token(LCBR);
Core.setEagerFlowing(false); SymbolTable.EnterBlock(null); blockFlow = new BlockFlow();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LCBR:
      case LSBR:
      case IF:
      case WHILE:
      case SYMBOL:{
        ;
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        break label_2;
      }
      topFlow = flowing();
blockFlow.addFlow(topFlow);
    }
    jj_consume_token(RCBR);
Core.setEagerFlowing(true); SymbolTable.ExitBlock();
//        System.err.println("blcokFlow: "+blockFlow);
        {if ("" != null) return blockFlow;}
    throw new Error("Missing return statement in function");
}

  final public Flowable flow() throws ParseException {Flowable flow = new ListFlow();
    Token flowSymbol;
    if (jj_2_1(2)) {
      flow = func_flow();
    } else if (jj_2_2(3)) {
      flow = head_tail_flow();
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case SYMBOL:{
        flowSymbol = jj_consume_token(SYMBOL);
flow = SymbolTable.CurrentSymbolTable().PutOrGetSymbol(flowSymbol.image, SymbolTable.SymbolType.Flow).assertGetFlowable();
        break;
        }
      case LSBR:{
        flow = list_flow();
        break;
        }
      case IF:{
        flow = if_else_flow();
        break;
        }
      case WHILE:{
        flow = while_flow();
        break;
        }
      case LCBR:{
        flow = block_flow();
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
{if ("" != null) return flow;}
    throw new Error("Missing return statement in function");
}

  final public Flowable flowing() throws ParseException {Flowable topFlow = new ListFlow();
    Flowable leftFlow = new ListFlow();
    Flowable rightFlow = new ListFlow();
    // 语法：match_flow() = flow() < FLOW > flow()
        topFlow = flow();
leftFlow = topFlow;
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case FLOW:{
        ;
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        break label_3;
      }
      jj_consume_token(FLOW);
      rightFlow = flow();
leftFlow.SetNext(rightFlow);
//                                        System.err.println(leftFlow.GetSymbol() +leftFlow.GetIdentity()+ "->"+rightFlow.GetSymbol()+rightFlow.GetIdentity());
//                                        System.err.println("leftFlow:\n"+leftFlow);
//                                        System.err.println("rightFlow:\n"+rightFlow);
//                                        System.err.println("symboltable:\n"+SymbolTable.CurrentSymbolTable());
        leftFlow = rightFlow;
    }
//        System.out.println("Root Symbol Table: " + SymbolTable.CurrentSymbolTable());
//        System.out.println("before flowing: " + topFlow);
        if (Core.isEagerFlowing()) {
//            System.out.println("eager! flowing");
            topFlow.Flowing();
        }
//        System.out.println("after flowing: " + topFlow);
//                                        System.err.println("topFlow:\n"+topFlow);
//                                                System.err.println("symboltable:\n"+SymbolTable.CurrentSymbolTable());
        {if ("" != null) return topFlow;}
    throw new Error("Missing return statement in function");
}

  final public void def_func_stmt() throws ParseException {Token func_symbol;
    ListFlow param_flow = null;
    BlockFlow block_flow;
    jj_consume_token(FUNC);
    func_symbol = jj_consume_token(SYMBOL);
    jj_consume_token(LBR);
SymbolTable.EnterBlock(func_symbol.image);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LSBR:{
      param_flow = list_flow();
      break;
      }
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    jj_consume_token(RBR);
    block_flow = block_flow();
SymbolTable.ExitBlock();
SymbolTable.CurrentSymbolTable().PutSymbol(func_symbol.image, SymbolTable.SymbolType.Flow, new FuncFlow(func_symbol.image, param_flow, block_flow));
}

  final public void import_stmt() throws ParseException {Token package_name;
    Token module_name;
    jj_consume_token(IMPORT);
    package_name = jj_consume_token(SYMBOL);
    jj_consume_token(DOT);
    module_name = jj_consume_token(SYMBOL);
try {
             Class<?> clazz = Class.forName(package_name.image + "." + module_name.image);
             Method loadModule = clazz.getMethod(Core.ModuleLoadFunc);
             loadModule.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException ex) {
            {if (true) throw new RuntimeException(ex.getMessage());}
        }
}

  final public void stmt() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LCBR:
    case LSBR:
    case IF:
    case WHILE:
    case SYMBOL:{
      flowing();
      break;
      }
    case FUNC:{
      def_func_stmt();
      break;
      }
    case IMPORT:{
      import_stmt();
{if ("" != null) return;}
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
}

  final public void stmts() throws ParseException {
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case LCBR:
      case LSBR:
      case IF:
      case WHILE:
      case FUNC:
      case IMPORT:
      case SYMBOL:{
        ;
        break;
        }
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      stmt();
    }
{if ("" != null) return;}
}

  final public void program() throws ParseException {
    stmts();
    jj_consume_token(0);
{if ("" != null) return;}
}

  private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_1()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_2()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  private boolean jj_3R_func_flow_299_5_5()
 {
    if (jj_scan_token(SYMBOL)) return true;
    if (jj_scan_token(LBR)) return true;
    return false;
  }

  private boolean jj_3R_symbol_data_186_5_7()
 {
    if (jj_scan_token(SYMBOL)) return true;
    return false;
  }

  private boolean jj_3_2()
 {
    if (jj_3R_head_tail_flow_314_5_6()) return true;
    return false;
  }

  private boolean jj_3_1()
 {
    if (jj_3R_func_flow_299_5_5()) return true;
    return false;
  }

  private boolean jj_3R_head_tail_flow_314_5_6()
 {
    if (jj_scan_token(LSBR)) return true;
    if (jj_3R_symbol_data_186_5_7()) return true;
    if (jj_scan_token(SEMIC)) return true;
    return false;
  }

  /** Generated Token Manager. */
  public FldwCompilerTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  private int jj_gen;
  final private int[] jj_la1 = new int[11];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x0,0xf0000000,0x0,0x100,0x0,0x5000,0x5000,0x0,0x4000,0x5000,0x5000,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x8700,0x0,0x18700,0x0,0x2,0x10005,0x10005,0x10,0x0,0x1004d,0x1004d,};
	}
  final private JJCalls[] jj_2_rtns = new JJCalls[2];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  /** Constructor with InputStream. */
  public FldwCompiler(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public FldwCompiler(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new FldwCompilerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 11; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 11; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public FldwCompiler(java.io.Reader stream) {
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new FldwCompilerTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 11; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new FldwCompilerTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 11; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public FldwCompiler(FldwCompilerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 11; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(FldwCompilerTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 11; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   if (++jj_gc > 100) {
		 jj_gc = 0;
		 for (int i = 0; i < jj_2_rtns.length; i++) {
		   JJCalls c = jj_2_rtns[i];
		   while (c != null) {
			 if (c.gen < jj_gen) c.first = null;
			 c = c.next;
		   }
		 }
	   }
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error {
    @Override
    public Throwable fillInStackTrace() {
      return this;
    }
  }
  static private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  private boolean jj_scan_token(int kind) {
	 if (jj_scanpos == jj_lastpos) {
	   jj_la--;
	   if (jj_scanpos.next == null) {
		 jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
	   } else {
		 jj_lastpos = jj_scanpos = jj_scanpos.next;
	   }
	 } else {
	   jj_scanpos = jj_scanpos.next;
	 }
	 if (jj_rescan) {
	   int i = 0; Token tok = token;
	   while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
	   if (tok != null) jj_add_error_token(kind, i);
	 }
	 if (jj_scanpos.kind != kind) return true;
	 if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
	 return false;
  }


/** Get the next Token. */
  final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
	 if (pos >= 100) {
		return;
	 }

	 if (pos == jj_endpos + 1) {
	   jj_lasttokens[jj_endpos++] = kind;
	 } else if (jj_endpos != 0) {
	   jj_expentry = new int[jj_endpos];

	   for (int i = 0; i < jj_endpos; i++) {
		 jj_expentry[i] = jj_lasttokens[i];
	   }

	   for (int[] oldentry : jj_expentries) {
		 if (oldentry.length == jj_expentry.length) {
		   boolean isMatched = true;

		   for (int i = 0; i < jj_expentry.length; i++) {
			 if (oldentry[i] != jj_expentry[i]) {
			   isMatched = false;
			   break;
			 }

		   }
		   if (isMatched) {
			 jj_expentries.add(jj_expentry);
			 break;
		   }
		 }
	   }

	   if (pos != 0) {
		 jj_lasttokens[(jj_endpos = pos) - 1] = kind;
	   }
	 }
  }

  /** Generate ParseException. */
  public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[50];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 11; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 50; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 jj_endpos = 0;
	 jj_rescan_token();
	 jj_add_error_token(0, 0);
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  private boolean trace_enabled;

/** Trace enabled. */
  final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

  private void jj_rescan_token() {
	 jj_rescan = true;
	 for (int i = 0; i < 2; i++) {
	   try {
		 JJCalls p = jj_2_rtns[i];

		 do {
		   if (p.gen > jj_gen) {
			 jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
			 switch (i) {
			   case 0: jj_3_1(); break;
			   case 1: jj_3_2(); break;
			 }
		   }
		   p = p.next;
		 } while (p != null);

		 } catch(LookaheadSuccess ls) { }
	 }
	 jj_rescan = false;
  }

  private void jj_save(int index, int xla) {
	 JJCalls p = jj_2_rtns[index];
	 while (p.gen > jj_gen) {
	   if (p.next == null) { p = p.next = new JJCalls(); break; }
	   p = p.next;
	 }

	 p.gen = jj_gen + xla - jj_la; 
	 p.first = token;
	 p.arg = xla;
  }

  static final class JJCalls {
	 int gen;
	 Token first;
	 int arg;
	 JJCalls next;
  }

}
