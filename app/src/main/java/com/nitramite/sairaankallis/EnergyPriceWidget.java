package com.nitramite.sairaankallis;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class EnergyPriceWidget extends AppWidgetProvider implements FingridInterface {

    private final Fingrid fingrid = new Fingrid(this);
    private Context context;
    private AppWidgetManager appWidgetManager;
    private int[] appWidgetIds;


    static void updateAppWidget(
            Context context,
            AppWidgetManager appWidgetManager,
            int appWidgetId,
            GridData gridData
    ) {
        String widgetUsageText = gridData.getConsumptionText();
        String widgetPriceText = gridData.getElectricityPriceInFinlandCentsKilowattHour();

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.energy_price);
        views.setTextViewText(R.id.usage_text, widgetUsageText);
        views.setTextViewText(R.id.price_text, widgetPriceText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        this.context = context;
        this.appWidgetManager = appWidgetManager;
        this.appWidgetIds = appWidgetIds;
        this.fingrid.GetGridData();
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void getDataSuccess(GridData gridData) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, gridData);
        }
    }

    @Override
    public void getDataFailed(String reason) {
    }

}