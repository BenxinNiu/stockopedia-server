package MUN.MongoDocument;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QOptionContractsConsolidator is a Querydsl query type for OptionContractsConsolidator
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QOptionContractsConsolidator extends EntityPathBase<OptionContractsConsolidator> {

    private static final long serialVersionUID = 1834617412L;

    public static final QOptionContractsConsolidator optionContractsConsolidator = new QOptionContractsConsolidator("optionContractsConsolidator");

    public final StringPath _id = createString("_id");

    public final StringPath central_index_key = createString("central_index_key");

    public final StringPath company_name = createString("company_name");

    public final StringPath expiration = createString("expiration");

    public final StringPath identifier = createString("identifier");

    public final StringPath isExpired = createString("isExpired");

    public final StringPath legal_entity_identifier = createString("legal_entity_identifier");

    public final NumberPath<Double> strike = createNumber("strike", Double.class);

    public final StringPath ticker = createString("ticker");

    public final StringPath type = createString("type");

    public QOptionContractsConsolidator(String variable) {
        super(OptionContractsConsolidator.class, forVariable(variable));
    }

    public QOptionContractsConsolidator(Path<? extends OptionContractsConsolidator> path) {
        super(path.getType(), path.getMetadata());
    }

    public QOptionContractsConsolidator(PathMetadata metadata) {
        super(OptionContractsConsolidator.class, metadata);
    }

}

