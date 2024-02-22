package commons;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private Map<String,Object> scenarioContext;

    public TestContext(){
        scenarioContext = new HashMap<>();
    }

    public void setContext(String key,Object object){
        scenarioContext.put(key,object);
    }

    public Object getContext(String key){
       return scenarioContext.get(key);
    }

    public Boolean isContains(String key){
        return scenarioContext.containsKey(key);
    }
}
