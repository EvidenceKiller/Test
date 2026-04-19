package com.adl.service.repository.persist;

import com.adl.service.common.FileDownManager;
import com.adl.service.data.BasePageData;
import com.adl.service.data.DictData;
import com.adl.service.data.LoginInfoData;
import com.adl.service.data.StudentData;
import com.adl.service.data.TeacherData;
import com.adl.service.db.DaoManagerProxy;
import com.adl.service.db.entity.DictEntity;
import com.adl.service.db.entity.LoginInfoEntity;
import com.adl.service.db.entity.StudentEntity;
import com.adl.service.db.entity.TeacherEntity;
import com.adl.service.exception.NzCommonException;
import com.adl.service.http.request.DictRequest;
import com.adl.service.repository.prepare.CacheTag;
import com.adl.service.repository.prepare.MemoryCacheManager;
import com.adl.service.utils.InnerUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.functions.Function;

public final class BigScreenH5PersistHelper {

    public BigScreenH5PersistHelper() {
    }

}
