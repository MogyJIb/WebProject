package by.gstu.training.models;

public class TableInform {
    public final static TableInform ACCOUNTS_INFORM =
            new TableInform("Accounts",
                    new String[]{
                    "AccountId",
                            "Login",
                            "Password",
                            "AccessLevel"
                    });

    public final static TableInform USERS_INFORM  =
            new TableInform("Users",
                    new String[]{
                            "UserId",
                            "FirstName",
                            "LastName",
                            "PhoneNumber",
                            "PassportNumber",
                            "PassportSeries",
                            "Discount",
                            "AccountId"
                    });


    public final static TableInform TOURS_INFORM  =
            new TableInform("Tours",
                    new String[]{
                            "TourId",
                            "Name",
                            "StartDate",
                            "EndDate",
                            "Price",
                            "IsSelled",
                            "IsBurning",
                            "Type",
                            "UserId",
                            "TravelAgentId"
                    });


    public final static TableInform AGENCIES_INFORM  =
            new TableInform("Agencies",
                    new String[]{
                            "AgencyId",
                            "Title",
                            "PhoneNumber"
                    });

    public final static TableInform TRAVELAGENTS_INFORM  =
            new TableInform("TravelAgents",
                    new String[]{
                            "TravelAgentId",
                            "Name",
                            "AgencyId",
                            "AccountId",
                            "Salary"
                    });




    private String tableName;
    private String idColumnName;
    private String parameters;
    private String insertedParameters;
    private String updatedParameters;
    private int countParameters;


    private TableInform(String tableName, String[] columns) {
        this.tableName = tableName;
        this.countParameters = columns.length;
        this.idColumnName = columns[0];
        this.insertedParameters  = makeInsertedParameters(countParameters);
        this.updatedParameters = makeUpdatedParameters(columns);
        this.parameters = makeParameters(columns);
    }

    private String makeInsertedParameters(int count){
        count--;
        String res = "(";
        for(int i=0; i< count-1;i++){
            res += "?,";
        }
        if(count>1)
            res += "?)";
        return res;
    }

    private String makeUpdatedParameters(String[] columns){
        String res = "";
        for(int i=1; i< columns.length-1;i++){
            res += columns[i]+ "=?,";
        }
        if(columns.length-1>=0)
            res += columns[columns.length-1]+"=?";
        return res;
    }

    private String makeParameters(String[] columns){
        if(columns.length<2)
            return "";

        String res = "(";
        for(int i=1; i< columns.length-1;i++){
            res += columns[i]+ ",";
        }
        res += columns[columns.length-1]+")";
        return res;
    }

    public String getTableName() {
        return tableName;
    }

    public String getIdColumnName() {
        return idColumnName;
    }

    public String getParameters() {
        return parameters;
    }

    public String getInsertedParameters() {
        return insertedParameters;
    }

    public String getUpdatedParameters() {
        return updatedParameters;
    }

    public int getCountParameters() {
        return countParameters;
    }
}
