package cn.edu.scau.DataCollectionSystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class PagingRequest {

    private int pageNum;

    private int pageSize;

}
