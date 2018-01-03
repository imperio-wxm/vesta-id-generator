package com.robert.vesta.service.impl;

import com.robert.vesta.service.bean.Id;
import com.robert.vesta.service.impl.bean.IdType;
import com.robert.vesta.service.impl.populater.AtomicIdPopulator;
import com.robert.vesta.service.impl.populater.IdPopulator;
import com.robert.vesta.service.impl.populater.LockIdPopulator;
import com.robert.vesta.service.impl.populater.SyncIdPopulator;
import com.robert.vesta.util.CommonUtils;

public class IdServiceImpl extends AbstractIdServiceImpl {

    private static final String SYNC_LOCK_IMPL_KEY = "vesta.sync.lock.impl.key";

    private static final String ATOMIC_IMPL_KEY = "vesta.atomic.impl.key";

    private IdPopulator idPopulator;

    public IdServiceImpl() {
        super();

        initPopulator();
    }

    public IdServiceImpl(String type) {
        super(type);

        initPopulator();
    }

    public IdServiceImpl(IdType type) {
        super(type);

        initPopulator();
    }

    public void initPopulator() {
        if (CommonUtils.isPropKeyOn(SYNC_LOCK_IMPL_KEY))
            idPopulator = new SyncIdPopulator();
        else if (CommonUtils.isPropKeyOn(ATOMIC_IMPL_KEY))
            idPopulator = new AtomicIdPopulator();
        else
            idPopulator = new LockIdPopulator();
    }

    protected void populateId(Id id) {
        idPopulator.populateId(id, this.idMeta);
    }
}
