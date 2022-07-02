package com.nitramite.sairaankallis;

import android.content.Context;

import java.text.DecimalFormat;

public class GridData {

    private final DecimalFormat df = new DecimalFormat("0.00");

    private double ElectricityPriceInFinland = 0.0;
    private double Consumption = 0.0;
    private double Production = 0.0;
    private double NetImportExport = 0.0;
    private int Status = 1;

    public void setElectricityPriceInFinland(double electricityPriceInFinland) {
        ElectricityPriceInFinland = electricityPriceInFinland;
    }

    public double getElectricityPriceInFinland() {
        return ElectricityPriceInFinland;
    }

    public void setConsumption(double consumption) {
        Consumption = consumption;
    }

    public double getConsumption() {
        return Consumption;
    }

    public String getConsumptionText() {
        return String.valueOf((int) Consumption);
    }

    public void setProduction(double production) {
        Production = production;
    }

    public double getProduction() {
        return Production;
    }

    public void setNetImportExport(double netImportExport) {
        NetImportExport = netImportExport;
    }

    public double getNetImportExport() {
        return NetImportExport;
    }

    public String getElectricityPriceInFinlandCentsKilowattHour() {
        double priceCents = (ElectricityPriceInFinland / 1000) * 100;
        return df.format(priceCents);
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getStatusString(Context context) {
        return Status == 1 ? context.getString(R.string.status_normal) : context.getString(R.string.status_issue);
    }

}
