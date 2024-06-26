package com.cherryjava.framework.i18n;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import com.cherryjava.common.config.ProjectConfig;
import com.cherryjava.common.exception.error.ErrorCode;
import com.cherryjava.common.exception.error.ErrorCodeInterface;
import com.cherryjava.common.utils.i18n.MessageUtils;
import com.cherryjava.framework.exception.GlobalExceptionFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 检测 未添加到i18n文件(messages.properties)中的message
 *
 * @author hjamm
 */
@Component
public class MessageI18nCheckerRunner implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionFilter.class);
    private ProjectConfig projectConfig;

    public MessageI18nCheckerRunner(ProjectConfig projectConfig) {
        this.projectConfig = projectConfig;
    }

    public static Object[] allErrorCodes = ArrayUtil.addAll(
            ErrorCode.Internal.values(),
            ErrorCode.External.values(),
            ErrorCode.Client.values(),
            ErrorCode.Business.values());

    @Override
    public void run(ApplicationArguments args) {
        if (Convert.toBool(projectConfig.getCheckI18nKey())) {
            checkEveryMessage();
        }
    }

    /**
     * 如果想支持i18n, 请把对应的错误码描述填到 /resources/i18n/messages.properties 文件中
     */
    public void checkEveryMessage() {
        for (Object errorCode : allErrorCodes) {
            ErrorCodeInterface errorInterface = (ErrorCodeInterface) errorCode;
            try {
                MessageUtils.message(errorInterface.i18nKey());
            } catch (Exception e) {
                log.warn("could not find i18n message for:{}  in the file /resources/i18n/messages.properties.",
                        errorInterface.i18nKey());
            }
        }
    }
}
