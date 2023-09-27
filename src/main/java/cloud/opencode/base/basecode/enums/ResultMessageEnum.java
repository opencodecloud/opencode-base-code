package cloud.opencode.base.basecode.enums;

/**
 * @author Jon So,
 * e-mail: ijonso123@gmail.com
 * url: <a href="https://jon.wiki">Jon's blog</a>
 * url: <a href="https://github.com/opencodecloud">project github</a>
 * @version v1.0.0
 */
public enum ResultMessageEnum {
    SYSTEM_ERROR("System error!"),
    SYSTEM_SUCCESS("Everything are well done!"),
    SYSTEM_OK("OK!");
    String value;

    ResultMessageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}