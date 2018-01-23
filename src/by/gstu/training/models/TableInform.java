package by.gstu.training.models;

public class TableInform {
    public final static TableInform ACCOUNTS_INFORM =
            new TableInform("Accounts",
                    "AccountId",
                    "(?,?,?,?)",
                    "AccountId= ?, Login = ?, Password = ?, AccessLevel = ?");

    public final static TableInform USERS_INFORM  =
            new TableInform("Users",
                    "UserId",
                    "(?,?,?,?,?,?,?)",
                    "UserId= ?, FirstName = ?, LastName = ?, PhoneNumber = ?, PassportNumber = ?, PassportSeries = ?, AccountId = ?");

    public final static TableInform TOURS_INFORM  =
            new TableInform("Tours",
                    "TourId",
                    "(?,?,?,?,?,?,?)",
                    "TourId= ?, StartDate = ?, EndDate = ?, Price = ?, Type = ?, ClientAccountId = ?, TravelAgentId = ?");

    public final static TableInform AGENCIES_INFORM  =
            new TableInform("Agencies",
                    "AgencyId",
                    "(?,?,?)",
                    "AgencyId= ?, Title = ?, PhoneNumber = ?");

    public final static TableInform TRAVELAGENTS_INFORM  =
            new TableInform("TravelAgents",
                    "TravelAgentId",
                    "(?,?,?,?,?)",
                    "TravelAgentId= ?, Name = ?, AgencyId = ?, AccountId = ?, Salary = ?");



    private String tableName;
    private String idColumnName;
    private String insertedParameters;
    private String updatedParameters;
    private int countParameters;


    private TableInform(String tableName, String idColumnName, String insertedParameters, String updatedParameters) {
        this.tableName = tableName;
        this.idColumnName = idColumnName;
        this.insertedParameters = insertedParameters;
        this.updatedParameters = updatedParameters;
        this.countParameters = insertedParameters.split("\\?").length-1;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIdColumnName() {
        return idColumnName;
    }

    public void setIdColumnName(String idColumnName) {
        this.idColumnName = idColumnName;
    }

    public String getInsertedParameters() {
        return insertedParameters;
    }

    public void setInsertedParameters(String insertedParameters) {
        this.insertedParameters = insertedParameters;
    }

    public String getUpdatedParameters() {
        return updatedParameters;
    }

    public void setUpdatedParameters(String updatedParameters) {
        this.updatedParameters = updatedParameters;
    }

    public int getCountParameters() {
        return countParameters;
    }
}
