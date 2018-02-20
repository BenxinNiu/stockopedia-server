package MUN.MongoDocument;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTransactionConsolidator is a Querydsl query type for TransactionConsolidator
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTransactionConsolidator extends EntityPathBase<TransactionConsolidator> {

    private static final long serialVersionUID = 1948371446L;

    public static final QTransactionConsolidator transactionConsolidator = new QTransactionConsolidator("transactionConsolidator");

    public final StringPath _id = createString("_id");

    public final StringPath ask_price = createString("ask_price");

    public final StringPath book_value = createString("book_value");

    public final StringPath client_id = createString("client_id");

    public final BooleanPath fullfilled = createBoolean("fullfilled");

    public final StringPath fullfilled_date = createString("fullfilled_date");

    public final StringPath ticker = createString("ticker");

    public final StringPath trans_date = createString("trans_date");

    public final StringPath trans_type = createString("trans_type");

    public final StringPath trans_volume = createString("trans_volume");

    public QTransactionConsolidator(String variable) {
        super(TransactionConsolidator.class, forVariable(variable));
    }

    public QTransactionConsolidator(Path<? extends TransactionConsolidator> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransactionConsolidator(PathMetadata metadata) {
        super(TransactionConsolidator.class, metadata);
    }

}

