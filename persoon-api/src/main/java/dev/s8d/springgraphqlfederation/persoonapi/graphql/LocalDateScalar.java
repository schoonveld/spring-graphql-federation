package dev.s8d.springgraphqlfederation.persoonapi.graphql;

import graphql.language.StringValue;
import graphql.schema.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateScalar {

    public static final GraphQLScalarType INSTANCE = GraphQLScalarType.newScalar()
            .name("LocalDate")
            .description("Java LocalDate as scalar")
            .coercing(new Coercing<LocalDate, String>() {
                @Override
                public String serialize(Object dataFetcherResult) {
                    if (dataFetcherResult instanceof LocalDate) {
                        return ((LocalDate) dataFetcherResult).toString();
                    }
                    throw new CoercingSerializeException("Expected a LocalDate object.");
                }

                @Override
                public LocalDate parseValue(Object input) {
                    try {
                        return LocalDate.parse(input.toString());
                    } catch (DateTimeParseException e) {
                        throw new CoercingParseValueException("Invalid LocalDate format.");
                    }
                }

                @Override
                public LocalDate parseLiteral(Object input) {
                    if (input instanceof StringValue) {
                        try {
                            return LocalDate.parse(((StringValue) input).getValue());
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseLiteralException("Invalid LocalDate literal.");
                        }
                    }
                    throw new CoercingParseLiteralException("Expected a StringValue.");
                }
            })
            .build();
}
