package cash;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStorage {
    private final ConcurrentHashMap<Integer, Account> accounts = new ConcurrentHashMap<>();

    public synchronized boolean add(Account account) {
        return (accounts.put(account.id(), account) != null);
    }

    public synchronized boolean update(Account account) {
        boolean result = false;
        for (Map.Entry<Integer, Account> item : accounts.entrySet()) {
            if (item.getKey() != null && item.getValue().id() == account.id()) {
                accounts.put(item.getKey(), account);
                result = true;
            }
        }
        return result;
    }

    public synchronized void delete(int id) {
        for (Map.Entry<Integer, Account> item : accounts.entrySet()) {
            if (item.getKey() != null && item.getValue().id() == id) {
                accounts.remove(item.getKey());
            }
        }
    }

    public synchronized Optional<Account> getById(int id) {
        Optional<Account> result = Optional.empty();
        for (Map.Entry<Integer, Account> item : accounts.entrySet()) {
            if (item.getKey() != null && item.getValue().id() == id) {
                result = Optional.of(item.getValue());
            }
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<Account> from = getById(fromId);
        Optional<Account> to = getById(toId);
        boolean result = false;
        if (from.isPresent() && to.isPresent() && from.get().amount() >= amount && amount > 0) {
            update(new Account(from.get().id(), from.get().amount() - amount));
            update(new Account(to.get().id(), to.get().amount() + amount));
            result = true;
        }
        return result;
    }
}
