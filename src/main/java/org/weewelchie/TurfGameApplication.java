package org.weewelchie;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.runtime.Application;

@OpenAPIDefinition(
    tags = {
            @Tag(name="Turfgame", description="Turfgame operations.")
    },
    info = @Info(
        title="Turfgame-Stats",
        version = "1.0.1-SNAPSHOT",
        contact = @Contact(
            name = "Turfgame Stats AP",
            url = "http://turfgame.weewelchie.org",
            email = "chris@weewelchie.org"),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"))
)

public class TurfGameApplication extends Application{

    @Override
    protected void doStart(String[] args) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void doStop() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
