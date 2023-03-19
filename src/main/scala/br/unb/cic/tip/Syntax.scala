package br.unb.cic.tip


/**
 * The abstract syntax definition of the Tiny Imperative
 * Language.
 */
type Program = List[FunDecl]

type Int = Integer
type Id  = String
type Field = (Id, Expression)

/** Algebraic definition of function declaration.
 *   - name: name of the function
 *   - args: formal arguments of the function
 *   - vars: local variables of the function
 *   - body: stmt corresponding to the function body
 *   - retExp: the return expression of the function
 *
 *   Concrete Syntax           |      Abstract Syntex
 *   soma(a, b) {              |
 *      return a + b;    => parser => FunDecl("soma", ["a", "b"], [], , AddExp(VariableExp("a"), VariableExp("b"))
 *   }
 */
case class FunDecl(name: Id, args: List[Id], vars: List[Id], body: Stmt, retExp: Expression)

/** Algebraic data type for expressions */
enum Expression:
  case ConstExp(v: Integer) extends Expression  // 0 | 1 | -1 | 2 | -2 | ...
  case VariableExp(name: Id) extends Expression // x | y | z | . . .
  case AddExp(left: Expression, right: Expression) extends Expression // Exp + Exp
  case SubExp(left: Expression, right: Expression) extends Expression // Exp - Exp
  case MultiExp(left: Expression, right: Expression) extends Expression // Exp * Exp
  case DivExp(left: Expression, right: Expression) extends Expression // Exp / Exp
  case EqExp(left: Expression, right: Expression) extends Expression // Exp == Exp
  case GTExp(left: Expression, right: Expression) extends Expression // Exp > Exp
  case BracketExp(exp: Expression) extends Expression // (Exp)

  // function-call expressions
  case DirectFunctionCallExp(name: Id, args: List[Expression]) extends Expression // Id ( Exp,. . . ,Exp )
  case IndirectFunctionCallExp(exp: Expression, args: List[Expression]) extends Expression // Exp ( Exp , . . . , Exp )

  // pointer-based expressions
  case AllocExp(exp: Expression) extends Expression // alloc Exp
  case LocationExp(pointer: Id) extends Expression // & Id
  case LoadExp(exp: Expression) extends Expression // * Exp
  case NullExp extends Expression // null

  // record-based expressions
  case RecordExp(fields: List[Field]) extends Expression // { Id : Exp , . . . , Id : Exp }
  case FieldAccess(record: Expression, field: Id)  // Exp . Id
  case InputExp extends Expression // input

/** Algebraic data type for statements */
enum Stmt:
  case AssignmentStmt(name: Id, exp: Expression) extends Stmt // Id = Exp
  case IfElseStmt(condition: Expression, s1: Stmt, s2: Option[Stmt]) extends Stmt // if ( Exp ) { Stmt } [else { Stmt }]
  case WhileStmt(condition: Expression, stmt: Stmt) extends Stmt // while ( Exp ) { Stmt }
  case SequenceStmt(s1: Stmt, s2: Stmt) extends Stmt // Stmt Stmt
  case StoreStmt(exp1: Expression, exp2: Expression) extends Stmt // *Exp = Exp
  case OutputStmt(exp: Expression) extends Stmt // output Exp
  case CallStmt(name: Id, function: Id) extends Stmt // output Exp
  case AfterCallStmt(function: Id, name: Id) extends Stmt // output Exp

/** Node Types */
enum Node:
  case StartNode extends Node
  case EndNode extends Node
  case SimpleNode(stmt: Stmt) extends Node


