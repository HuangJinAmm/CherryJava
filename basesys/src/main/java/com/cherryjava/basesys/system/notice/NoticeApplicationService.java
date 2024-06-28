package com.cherryjava.basesys.system.notice;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cherryjava.basesys.common.command.BulkOperationCommand;
import com.cherryjava.basesys.system.notice.command.NoticeAddCommand;
import com.cherryjava.basesys.system.notice.command.NoticeUpdateCommand;
import com.cherryjava.basesys.system.notice.db.SysNoticeEntity;
import com.cherryjava.basesys.system.notice.db.SysNoticeService;
import com.cherryjava.basesys.system.notice.dto.NoticeDTO;
import com.cherryjava.basesys.system.notice.model.NoticeModel;
import com.cherryjava.basesys.system.notice.model.NoticeModelFactory;
import com.cherryjava.basesys.system.notice.query.NoticeQuery;
import com.cherryjava.common.core.page.PageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjamm
 */
@Service
public class NoticeApplicationService {

    private final SysNoticeService noticeService;

    private final NoticeModelFactory noticeModelFactory;

    public NoticeApplicationService(SysNoticeService noticeService, NoticeModelFactory noticeModelFactory) {
        this.noticeService = noticeService;
        this.noticeModelFactory = noticeModelFactory;
    }

    public PageDTO<NoticeDTO> getNoticeList(NoticeQuery query) {
        Page<SysNoticeEntity> page = noticeService.getNoticeList(query.toPage(), query.toQueryWrapper());
        List<NoticeDTO> records = page.getRecords().stream().map(NoticeDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }


    public NoticeDTO getNoticeInfo(Long id) {
        NoticeModel noticeModel = noticeModelFactory.loadById(id);
        return new NoticeDTO(noticeModel);
    }


    public void addNotice(NoticeAddCommand addCommand) {
        NoticeModel noticeModel = noticeModelFactory.create();
        noticeModel.loadAddCommand(addCommand);

        noticeModel.checkFields();

        noticeModel.insert();
    }


    public void updateNotice(NoticeUpdateCommand updateCommand) {
        NoticeModel noticeModel = noticeModelFactory.loadById(updateCommand.getNoticeId());
        noticeModel.loadUpdateCommand(updateCommand);

        noticeModel.checkFields();

        noticeModel.updateById();
    }

    public void deleteNotice(BulkOperationCommand<Integer> deleteCommand) {
        noticeService.removeBatchByIds(deleteCommand.getIds());
    }


}
