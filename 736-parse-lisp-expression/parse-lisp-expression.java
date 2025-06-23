class Solution {
    public int evaluate(String expression) 
{
    return eval(expression, new HashMap<>());
}

private int eval(String expr, Map<String, Integer> scope) 
{
    // 1. If it's a number, return it directly
    if (Character.isDigit(expr.charAt(0)) || expr.charAt(0) == '-') 
        return Integer.parseInt(expr);

    // 2. If it's a variable, get its value from the scope
    if (Character.isLowerCase(expr.charAt(0)) && !expr.startsWith("(")) 
        return scope.get(expr);     //e.g. scope={ "x": 2, "y": 3 }  so return 2 for eval(x, scope)

    // 3. Remove outer parentheses and tokenize the expression
    expr=expr.substring(1, expr.length() - 1); // Remove outer parentheses
    List<String> tokens = new ArrayList<>();
    int balance=0, start=0;

    for (int i=0; i<expr.length(); i++) {
        char c=expr.charAt(i);
        if (c=='(') balance++;
        if (c==')') balance--;
        if (balance==0 && (c==' '||i==expr.length()-1)) 
        {//Splits tokens when we hit a space (c == ' ') or reach the last character (i == expr.length() - 1
            tokens.add(expr.substring(start, i+(i==expr.length()-1?1:0)).trim());     //If the current character is the last one, extend the substring to include it (i + 1).
            start=i+1;
        }
    }
    //e.g. tokens = ["let", "x", "2", "(mult x (let x 3 y 4 (add x y)))"];
    // 4. Parse operation and evaluate accordingly
    String operation = tokens.get(0);
    if (operation.equals("add")) 
        return eval(tokens.get(1), scope) + eval(tokens.get(2), scope);
    else if (operation.equals("mult"))   //tokens = ["mult", "x", "(let x 3 y 4 (add x y))"];
        return eval(tokens.get(1), scope) * eval(tokens.get(2), scope);     //get x val * get val of the other expr
    else                // "let" operation
    { 
        Map<String, Integer> newScope = new HashMap<>(scope); // Create a new scope // Scope: {}
        for (int i=1;i<tokens.size()-1;i+=2) 
            newScope.put(tokens.get(i), eval(tokens.get(i+1), newScope)); // Assign variables - newScope.put("x", eval("2", newScope)); // x = 2
        return eval(tokens.get(tokens.size()-1), newScope); // Evaluate the final expression - eval("(mult x (let x 3 y 4 (add x y)))", newScope);

    }
}
}