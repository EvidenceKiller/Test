package com.adl.service.db.entity;

import com.adl.service.data.SportSkuDescData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class SportSkuDescEntity {
    /**
     * id
     */
    private String id;

    /**
     * title
     */
    private String title;

    /**
     * context
     */
    private String context;

    public static SportSkuDescEntity convertToEntity(SportSkuDescData data) {
        if (data == null) {
            return null;
        }
        SportSkuDescEntity entity = new SportSkuDescEntity();
        entity.setId(data.getId());
        entity.setTitle(data.getTitle());
        entity.setContext(data.getContext());
        return entity;
    }

    public static SportSkuDescData convertToData(SportSkuDescEntity entity) {
        if (entity == null) {
            return null;
        }
        SportSkuDescData data = new SportSkuDescData();
        data.setId(entity.getId());
        data.setTitle(entity.getTitle());
        data.setContext(entity.getContext());
        return data;
    }
}
