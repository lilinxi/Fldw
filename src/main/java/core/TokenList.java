package core;

import java.util.ArrayList;

public class TokenList {
    private ArrayList<String> tokenList;

    public TokenList() {
        this.tokenList = new ArrayList<>();
    }

    public int GetIdentity() {
        return System.identityHashCode(this);
    }

    public void addToken(String token) {
        this.tokenList.add(token);
    }

    public void addList(TokenList tokenList) {
        this.tokenList.addAll(tokenList.tokenList);
    }

    public String getTokens() {
        StringBuffer buf = new StringBuffer();
        for (String token : this.tokenList) {
            buf.append(token);
            buf.append(" ");
        }
        return buf.toString();
    }
}
