import java.util.HashMap;
import java.util.Map;

public class ATM {
    private Map<String, Account> accounts;

    public ATM() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getUserId(), account);
    }

    public Account authenticate(String userId, String pin) {
        Account account = accounts.get(userId);
        if (account != null && account.validatePin(pin)) {
            return account;
        }
        return null;
    }
}
