package guice;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import javax.inject.Named;

public class GuiceModule extends AbstractModule {

    public static final String JODA_OBJECT_MAPPER = "JODA_OBJECT_MAPPER";

    @Override
    public void configure() {

    }

    @Provides
    @Named(JODA_OBJECT_MAPPER)
    public ObjectMapper objectMapperProvider() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        mapper.enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID);
        return mapper;
    }
}
