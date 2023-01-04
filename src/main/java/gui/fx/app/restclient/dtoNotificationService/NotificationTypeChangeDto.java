package gui.fx.app.restclient.dtoNotificationService;

public class NotificationTypeChangeDto {
    private Long id;
    private boolean name;
    private boolean lastName;
    private boolean link;
    private boolean idVehicle;
    private boolean model;
    private boolean typeOfVehicle;
    private boolean dateFrom;
    private boolean dateTo;
    private boolean emailOfManager;
    private boolean password;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isLastName() {
        return lastName;
    }

    public void setLastName(boolean lastName) {
        this.lastName = lastName;
    }

    public boolean isLink() {
        return link;
    }

    public void setLink(boolean link) {
        this.link = link;
    }

    public boolean isIdVehicle() {
        return idVehicle;
    }

    public void setIdVehicle(boolean idVehicle) {
        this.idVehicle = idVehicle;
    }

    public boolean isModel() {
        return model;
    }

    public void setModel(boolean model) {
        this.model = model;
    }

    public boolean isTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(boolean typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public boolean isDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(boolean dateFrom) {
        this.dateFrom = dateFrom;
    }

    public boolean isDateTo() {
        return dateTo;
    }

    public void setDateTo(boolean dateTo) {
        this.dateTo = dateTo;
    }

    public boolean isEmailOfManager() {
        return emailOfManager;
    }

    public void setEmailOfManager(boolean emailOfManager) {
        this.emailOfManager = emailOfManager;
    }

    public boolean isPassword() {
        return password;
    }

    public void setPassword(boolean password) {
        this.password = password;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
