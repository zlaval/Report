package com.zlrx.report;

import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class ReportGenerator {

    public static void main(String[] args) {

        try (OutputStream outputStream = new FileOutputStream("report.pdf")) {
            StyleBuilder boldCentered = stl.style()
                    .bold()
                    .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
            StyleBuilder columnTitleStyle = stl.style(boldCentered)
                    .setBorder(stl.pen1Point())
                    .setBackgroundColor(Color.LIGHT_GRAY);

            TextColumnBuilder<Integer> salaryColumnBuilder = Columns.column("Salary", "salary", DataTypes.integerType());
            TextColumnBuilder<String> nameColumnBuilder = Columns.column("Name", "name", DataTypes.stringType());

            Bar3DChartBuilder salaryChart = cht.bar3DChart()
                    .setTitle("Salary")
                    .setCategory(nameColumnBuilder)
                    .addSerie(cht.serie(salaryColumnBuilder));

            report()
                    .setColumnTitleStyle(columnTitleStyle)
                    .highlightDetailEvenRows()
                    .title(cmp.text("Salary").setStyle(boldCentered))
                    .columns(
                            nameColumnBuilder,
                            Columns.column("Job", "job", DataTypes.stringType()),
                            Columns.column("Address", "address", DataTypes.stringType()),
                            salaryColumnBuilder
                    )
                    .subtotalsAtSummary(sbt.sum(salaryColumnBuilder))
                    .summary(salaryChart)
                    .setDataSource(reportData())
                    .toPdf(outputStream);

        } catch (DRException | IOException e) {
            throw new RuntimeException(e);
        }

    }


    private static List<ReportRow> reportData() {
        List<ReportRow> rows = new LinkedList<>();

        rows.add(ReportRow.builder()
                .address("Budapest")
                .job("Developer")
                .name("Gipsz Jakab")
                .salary(2100)
                .build());

        rows.add(ReportRow.builder()
                .address("London")
                .job("Driver")
                .name("Mathew Smith")
                .salary(1700)
                .build());

        rows.add(ReportRow.builder()
                .address("Rome")
                .job("Teacher")
                .name("Carmen Cattaneo")
                .salary(1400)
                .build());

        rows.add(ReportRow.builder()
                .address("Tokio")
                .job("Developer")
                .name("Onishi Kantaro")
                .salary(3100)
                .build());

        rows.add(ReportRow.builder()
                .address("New York")
                .job("Nurse")
                .name("Daniel A. Miller")
                .salary(2300)
                .build());


        return rows;
    }


}
