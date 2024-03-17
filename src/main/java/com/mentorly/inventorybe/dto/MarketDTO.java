/**
 * @author Starling Diaz on 3/16/2024.
 * @Academy StarAcademy
 * @version inventory-be 1.0
 * @since 3/16/2024.
 */

package com.mentorly.inventorybe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MarketDTO {
    private Long id;
    private String name;
    private String location;
}
