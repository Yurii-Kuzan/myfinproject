package Servlets.admin;

import db.DBManager;
import db.entity.ManageReq;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "/saveReport")
public class ServletSaveReport extends HttpServlet {


    private final DBManager dbManager = DBManager.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Connection connection = dbManager.getConnection();
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        List<ManageReq> manageReqList = null;

        if (reportId == 1) {
            try {
                manageReqList = dbManager.FindReportByDate(connection);

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

        if (reportId == 2) {
            try {
                manageReqList = dbManager.FindReportByStatus(connection);

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

        if (reportId == 3) {
            try {
                manageReqList = dbManager.FindReportByCost(connection);

            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }

        HSSFWorkbook workbook = new HSSFWorkbook();

        /* creating page with name "Просто лист"*/
        HSSFSheet sheet = workbook.createSheet("Просто лист");

        // rows counter
        int rowNum = 0;

        // create name to columns (this will be the first row of page)
        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue("Номер заказа");
        row.createCell(1).setCellValue("Имя");
        row.createCell(2).setCellValue("Фамилия");
        row.createCell(3).setCellValue("Услуга ремонта");
        row.createCell(4).setCellValue("Цена");
        row.createCell(5).setCellValue("Статус");
        row.createCell(6).setCellValue("Дата заказа");
        row.createCell(7).setCellValue("Отзыв");


        // fill page with data
        for (ManageReq dataModel : manageReqList) {
            createSheetHeader(sheet, ++rowNum, dataModel);
        }

        try (FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Yurii\\Desktop\\myfinproject\\RepairReports.xls"))) {
            workbook.write(out);
        } catch (IOException e) {
            e.getMessage();
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment; filename=RepairReport.xls");

        File my_file = new File("C:\\Users\\Yurii\\Desktop\\myfinproject\\RepairReports.xls");

        // send file to response
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);

        byte[] buffer = new byte[4096];
        int length;

        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        // free resources
        in.close();
        out.flush();

    }

    /**
     * fill page with data
     */
    private static void createSheetHeader(HSSFSheet sheet, int rowNum, ManageReq dataModel) {

        Row row = sheet.createRow(rowNum);
        row.createCell(0).setCellValue(dataModel.getRequestId());
        row.createCell(1).setCellValue(dataModel.getFirstName());
        row.createCell(2).setCellValue(dataModel.getLastName());
        row.createCell(3).setCellValue(dataModel.getServiceName());
        row.createCell(4).setCellValue(dataModel.getCost());
        row.createCell(5).setCellValue(dataModel.getStatusName());
        row.createCell(6).setCellValue(dataModel.getRequestDate());
        row.createCell(7).setCellValue(dataModel.getFeedback());
    }


}
