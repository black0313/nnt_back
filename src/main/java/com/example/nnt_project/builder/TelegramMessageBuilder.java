package com.example.nnt_project.builder;

import com.example.nnt_project.entity.Load;
import com.example.nnt_project.entity.ShipperConsignee;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelegramMessageBuilder {

    public String buildMessage(Load load, List<ShipperConsignee> shipperConsigneeList) {
        StringBuilder message = new StringBuilder();
        message.append("\uD83D\uDE9A ").append(load.getTruck() != null ? load.getTruck().getTruckNumber() : " ")
                .append("\n\uD83D\uDD16 ").append(load.getDispatchersTeam().getName())
                .append("\n\uD83D\uDC68 ").append(load.getDriver() != null ? load.getDriver().getDriverName() : " ")
                .append("\n\n");

        // Append shippers/consignees
        for (ShipperConsignee shipperConsignee : shipperConsigneeList) {

            String address = shipperConsignee.getPickupAddress() != null ?
                    shipperConsignee.getPickupAddress().getAddress() : " ";

            String date = shipperConsignee.isShipper() ?
                    (shipperConsignee.getPickDate() != null ? shipperConsignee.getPickDate().toString() : "") :
                    (shipperConsignee.getDeliveryDate() != null ? shipperConsignee.getDeliveryDate().toString() : "");

            buildShipperConsigneeInfo(shipperConsignee, message, address, date);
        }


        // Append any other information
        message.append(buildLastStopInfo(load));

        return message.toString();
    }

    private void buildShipperConsigneeInfo(ShipperConsignee shipperConsignee, StringBuilder message, String address, String date) {
        // Shipper va consignee ma'lumotlarini formatlash
        if (shipperConsignee.getLastStop() == null && shipperConsignee.isShipper()) {
            message.append("Pick up: \uD83C\uDFED \n")
                    .append("<pre>").append(address).append("</pre>")
                    .append("\nArrive: ")
                    .append(date)
                    .append("\n\n");
        }

        if (shipperConsignee.getLastStop() != null && shipperConsignee.getLastStop()) {
            message.append("Last Stop: \uD83C\uDFED \n")
                    .append("<pre>").append(address).append("</pre>")
                    .append("\nArrive: ")
                    .append(date)
                    .append("\n\n");
        }

    }

    private String buildLastStopInfo(Load load) {
        // Yangi xabar qismini qo'shish
        return "⚠\uFE0F-Traffic/Construction/Weather or other delays (photos or videos) - should be updated in good time by drivers\n" +
                "⚠\uFE0F-Please Scale the load after pick up, to avoid axle overweight. Missing scale ticket - 200$ penalty fee.\n" +
                "⚠\uFE0F-After hooking up the trailer, PTI should be done !!!\n";
    }
}