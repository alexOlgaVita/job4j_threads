package cash;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStorage {
    private final ConcurrentHashMap<Integer, Account> accounts = new ConcurrentHashMap<>();

    public synchronized boolean add(Account account) {
        return (accounts.putIfAbsent(account.id(), account) == null);
    }

    public synchronized boolean update(Account account) {
        return (accounts.replace(account.id(), account) != null);
    }

    public synchronized void delete(int id) {
        accounts.remove(id);
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
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
