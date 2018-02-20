package MUN.MongoDocument;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDailyPriceConsolidator is a Querydsl query type for DailyPriceConsolidator
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDailyPriceConsolidator extends EntityPathBase<DailyPriceConsolidator> {

    private static final long serialVersionUID = 1468736054L;

    public static final QDailyPriceConsolidator dailyPriceConsolidator = new QDailyPriceConsolidator("dailyPriceConsolidator");

    public final StringPath _id = createString("_id");

    public final StringPath asofDate = createString("asofDate");

    public final NumberPath<Double> close = createNumber("close", Double.class);

    public final NumberPath<Double> high = createNumber("high", Double.class);

    public final NumberPath<Double> low = createNumber("low", Double.class);

    public final NumberPath<Double> open = createNumber("open", Double.class);

    public final StringPath snapshot_type = createString("snapshot_type");

    public final StringPath ticker = createString("ticker");

    public final StringPath trade_volume = createString("trade_volume");

    public QDailyPriceConsolidator(String variable) {
        super(DailyPriceConsolidator.class, forVariable(variable));
    }

    public QDailyPriceConsolidator(Path<? extends DailyPriceConsolidator> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDailyPriceConsolidator(PathMetadata metadata) {
        super(DailyPriceConsolidator.class, metadata);
    }

}

