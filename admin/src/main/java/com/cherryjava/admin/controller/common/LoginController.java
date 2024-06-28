package com.cherryjava.admin.controller.common;

import cn.hutool.core.util.StrUtil;
import com.cherryjava.admin.service.login.LoginService;
import com.cherryjava.admin.service.login.command.LoginCommand;
import com.cherryjava.admin.service.login.dto.CaptchaDTO;
import com.cherryjava.admin.service.login.dto.ConfigDTO;
import com.cherryjava.basesys.common.dto.CurrentLoginUserDTO;
import com.cherryjava.basesys.common.dto.TokenDTO;
import com.cherryjava.basesys.system.menu.MenuApplicationService;
import com.cherryjava.basesys.system.menu.dto.RouterDTO;
import com.cherryjava.basesys.system.user.UserApplicationService;
import com.cherryjava.basesys.system.user.command.AddUserCommand;
import com.cherryjava.common.config.ProjectConfig;
import com.cherryjava.common.core.dto.ResponseDTO;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import com.cherryjava.framework.annotations.ratelimit.RateLimit;
import com.cherryjava.framework.annotations.ratelimit.RateLimitKey;
import com.cherryjava.framework.user.AuthenticationUtils;
import com.cherryjava.framework.user.web.SystemLoginUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页
 *
 * @author valarchie
 */
@RestController
public class LoginController {

    private final LoginService loginService;

    private final MenuApplicationService menuApplicationService;

    private final UserApplicationService userApplicationService;

    private final ProjectConfig projectConfig;

    public LoginController(LoginService loginService, MenuApplicationService menuApplicationService, UserApplicationService userApplicationService, ProjectConfig agileBootConfig) {
        this.loginService = loginService;
        this.menuApplicationService = menuApplicationService;
        this.userApplicationService = userApplicationService;
        this.projectConfig = agileBootConfig;
    }

    /**
     * 访问首页，提示语
     */
    @GetMapping("/")
    @RateLimit(key = RateLimitKey.TEST_KEY, time = 10, maxCount = 5, cacheType = RateLimit.CacheType.Map,
            limitType = RateLimit.LimitType.GLOBAL)
    public String index() {
        return StrUtil.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。",
                projectConfig.getName(), projectConfig.getVersion());
    }


    /**
     * 获取系统的内置配置
     *
     * @return 配置信息
     */
    @GetMapping("/getConfig")
    public ResponseDTO<ConfigDTO> getConfig() {
        ConfigDTO configDTO = loginService.getConfig();
        return ResponseDTO.ok(configDTO);
    }

    /**
     * 生成验证码
     */
    @RateLimit(key = RateLimitKey.LOGIN_CAPTCHA_KEY, time = 10, maxCount = 10, cacheType = RateLimit.CacheType.REDIS,
            limitType = RateLimit.LimitType.IP)
    @GetMapping("/captchaImage")
    public ResponseDTO<CaptchaDTO> getCaptchaImg() {
        CaptchaDTO captchaImg = loginService.generateCaptchaImg();
        return ResponseDTO.ok(captchaImg);
    }

    /**
     * 登录方法
     *
     * @param loginCommand 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public ResponseDTO<TokenDTO> login(@RequestBody LoginCommand loginCommand) {
        // 生成令牌
        String token = loginService.login(loginCommand);
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        CurrentLoginUserDTO currentUserDTO = userApplicationService.getLoginUserInfo(loginUser);

        return ResponseDTO.ok(new TokenDTO(token, currentUserDTO));
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/getLoginUserInfo")
    public ResponseDTO<CurrentLoginUserDTO> getLoginUserInfo() {
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();

        CurrentLoginUserDTO currentUserDTO = userApplicationService.getLoginUserInfo(loginUser);

        return ResponseDTO.ok(currentUserDTO);
    }

    /**
     * 获取路由信息
     * TODO 如果要在前端开启路由缓存的话 需要在ServerConfig.json 中  设置CachingAsyncRoutes=true  避免一直重复请求路由接口
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public ResponseDTO<List<RouterDTO>> getRouters() {
        SystemLoginUser loginUser = AuthenticationUtils.getSystemLoginUser();
        List<RouterDTO> routerTree = menuApplicationService.getRouterTree(loginUser);
        return ResponseDTO.ok(routerTree);
    }


    @PostMapping("/register")
    public ResponseDTO<Void> register(@RequestBody AddUserCommand command) {
        return ResponseDTO.fail(new ApiException(ErrorCode.Business.COMMON_UNSUPPORTED_OPERATION));
    }

}
