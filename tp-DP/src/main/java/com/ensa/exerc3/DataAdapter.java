package com.ensa.exerc3;

import java.util.HashMap;
import java.util.Map;

class DataAdapter {

    private ExternalAPI externalAPI;

    public DataAdapter(ExternalAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    public void sendData(InternalData internalData) {
        Map<String, Object> adaptedData = new HashMap<>();
        adaptedData.put("first_name", internalData.getFirstName());
        adaptedData.put("last_name", internalData.getLastName());
        adaptedData.put("user_age", internalData.getAge());

        externalAPI.sendData(adaptedData);
    }

    public static void main(String[] args) {
        InternalData internalData = new InternalData("Alice", "Dupont", 30);
        ExternalAPI api = new ExternalAPI();
        DataAdapter adapter = new DataAdapter(api);

        adapter.sendData(internalData);
    }

}
