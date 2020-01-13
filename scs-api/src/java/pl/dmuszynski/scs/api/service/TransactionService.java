package pl.dmuszynski.scs.api.service;

import pl.dmuszynski.scs.api.model.Transaction;

public interface TransactionService {
    Transaction realizeTransaction(Transaction transaction);
}
