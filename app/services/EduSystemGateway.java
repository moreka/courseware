package services;

import exceptions.AuthenticationError;

import java.io.File;

public class EduSystemGateway {

    private static EduSystemGateway _instance = null;

    private EduSystemGateway() { }

    public static EduSystemGateway getInstance() {
        if (_instance == null)
            _instance = new EduSystemGateway();
        return _instance;
    }

    private String fetchPasswordFromConfigFile() {
        return "default password";
    }

    public File getCurrentSemesterData() throws AuthenticationError {
        return getSemesterData("");
    }

    public File getSemesterData(String semester) throws AuthenticationError {
        return new File("/Users/moreka/Downloads/edu-sample-excel-v3.0.xlsx");
    }
}
