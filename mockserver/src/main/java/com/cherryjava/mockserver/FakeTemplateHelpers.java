package com.cherryjava.mockserver;

import cn.hutool.core.util.RandomUtil;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.helpers.HandlebarsHelper;
import com.mifmif.common.regex.Generex;

import java.io.IOException;

public enum FakeTemplateHelpers implements Helper<Object> {
    /**
     * 中文姓名
     */
    CnName(new FakeName()),
    Regex(new GenerexHelper()),
    /**
     * 手机号
     */
    Phone(new GenerexHandleHelper("1[3456789]\\d{9}")),
    /**
     * 身份证
     */
    IdCard(new GenerexHandleHelper("[1-9]\\d{5}(19|20)\\d{2}((0[1-9)|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]")),
    /**
     * 邮件
     */
    Email(new GenerexHandleHelper("\\s{6,10}@(test|example).com(.cn)?"));

    private HandlebarsHelper<Object> handlebarsHelper;

    FakeTemplateHelpers(HandlebarsHelper<Object> handlebarsHelper) {
        this.handlebarsHelper = handlebarsHelper;
    }

    @Override
    public Object apply(Object o, Options options) throws IOException {
        return this.handlebarsHelper.apply(o, options);
    }
}

class FakeName extends HandlebarsHelper<Object> {
    private static String[] xing;
    private static String[] ming;

    static {
        xing = ("赵,钱,孙,李,周,吴,郑,王,冯,陈,褚,卫,蒋,沈,韩,杨,朱,秦,尤,许,\n" +
                "何,吕,施,张,孔,曹,严,华,金,魏,陶,姜,戚,谢,邹,喻,柏,水,窦,章,\n" +
                "云,苏,潘,葛,奚,范,彭,郎,鲁,韦,昌,马,苗,凤,花,方,俞,任,袁,柳,\n" +
                "酆,鲍,史,唐,费,廉,岑,薛,雷,贺,倪,汤,滕,殷,罗,毕,郝,邬,安,常,\n" +
                "乐,于,时,傅,皮,卞,齐,康,伍,余,元,卜,顾,孟,平,黄,和,穆,萧,尹,\n" +
                "姚,邵,湛,汪,祁,毛,禹,狄,米,贝,明,臧,计,伏,成,戴,谈,宋,茅,庞,\n" +
                "熊,纪,舒,屈,项,祝,董,粱,杜,阮,蓝,闵,席,季,麻,强,贾,路,娄,危,\n" +
                "江,童,颜,郭,梅,盛,林,刁,钟,徐,邱,骆,高,夏,蔡,田,樊,胡,凌,霍,\n" +
                "虞,万,支,柯,昝,管,卢,莫,经,房,裘,缪,干,解,应,宗,丁,宣,贲,邓,\n" +
                "郁,单,杭,洪,包,诸,左,石,崔,吉,钮,龚,程,嵇,邢,滑,裴,陆,荣,翁,\n" +
                "荀,羊,於,惠,甄,麴,家,封,芮,羿,储,靳,汲,邴,糜,松,井,段,富,巫,\n" +
                "乌,焦,巴,弓,牧,隗,山,谷,车,侯,宓,蓬,全,郗,班,仰,秋,仲,伊,宫,\n" +
                "宁,仇,栾,暴,甘,钭,厉,戎,祖,武,符,刘,景,詹,束,龙,叶,幸,司,韶,\n" +
                "郜,黎,蓟,薄,印,宿,白,怀,蒲,邰,从,鄂,索,咸,籍,赖,卓,蔺,屠,蒙,\n" +
                "池,乔,阴,欎,胥,能,苍,双,闻,莘,党,翟,谭,贡,劳,逄,姬,申,扶,堵,\n" +
                "冉,宰,郦,雍,舄,璩,桑,桂,濮,牛,寿,通,边,扈,燕,冀,郏,浦,尚,农,\n" +
                "温,别,庄,晏,柴,瞿,阎,充,慕,连,茹,习,宦,艾,鱼,容,向,古,易,慎,\n" +
                "戈,廖,庾,终,暨,居,衡,步,都,耿,满,弘,匡,国,文,寇,广,禄,阙,东,\n" +
                "殴,殳,沃,利,蔚,越,夔,隆,师,巩,厍,聂,晁,勾,敖,融,冷,訾,辛,阚,\n" +
                "那,简,饶,空,曾,毋,沙,乜,养,鞠,须,丰,巢,关,蒯,相,查,後,荆,红,\n" +
                "游,竺,权,逯,盖,益,桓,公,万俟,司马,上官,欧阳,夏侯,诸葛,\n" +
                "闻人,东方,赫连,皇甫,尉迟,公羊,澹台,公冶,宗政,濮阳,\n" +
                "淳于,单于,太叔,申屠,公孙,仲孙,轩辕,令狐,钟离,宇文,\n" +
                "长孙,慕容,鲜于,闾丘,司徒,司空,亓官,司寇,仉,督,子车,\n" +
                "颛孙,端木,巫马,公西,漆雕,乐正,壤驷,公良,拓跋,夹谷,\n" +
                "宰父,谷梁,晋,楚,闫,法,汝,鄢,涂,钦,段干,百里,东郭,南门,\n" +
                "呼延,归,海,羊舌,微生,岳,帅,缑,亢,况,后,有,琴,梁丘,左丘,\n" +
                "东门,西门,商,牟,佘,佴,伯,赏,南宫,墨,哈,谯,笪,年,爱,阳,佟,\n" +
                "第五,言,福,百,家,姓,终,爱新觉罗").split(",");
        ming = ("绍齐,博文,梓晨,胤祥,瑞,明哲,天,凯瑞,雄,杰,然,子涵,越彬,钰轩,智辉,致远,俊驰,雨泽,烨磊,志泽,子轩,春渊,晟香,文昊," +
                "修洁,黎听,远航,旭尧,鸿涛,伟祺,荣轩,越泽,浩字,瑾瑜,皓轩,苍,擎字,子骞,鹏涛,炎彬”+蠢磊,鹏煊,吴强,伟宸,博超,君浩," +
                "弘文,哲瀚,雨泽,楷瑞,建辉,晋鹏,天磊,绍辉,泽洋,烨伟,苑博,伟泽”+弘文,峻熙,嘉懿,煜城,懿轩,明辉,伟诚,明轩,健柏,修杰,志泽," +
                "鹤轩,越彬,风华,琪,峻熙,弘文,熠彤,鸿煊,炸霖”+明杰,立诚,立轩,立辉,正豪,吴然,熠彤,鸿,博涛,烨,烨生,煜祺,智宸,思淼,晓啸," +
                "天字,浩然,文轩,洋,振家”+展鹏,智渊,炫明,雪松,思源,笑思,志强,“,哲瀚,蠢鹏,吴天,!思聪,子默,思远,浩轩,语堂,聪健,明,文,果,思," +
                "驰,乐驹,晓博,文博,吴焱,立果,金,锦,嘉熙,鹏飞,丹,云,月,星,慈,飞,驰,杰,爱国,建军,八一").split(",");
    }


    @Override
    public Object apply(Object o, Options options) throws IOException {
        int i = RandomUtil.randomInt(xing.length);
        int j = RandomUtil.randomInt(ming.length);
        return xing[i] + ming[j];
    }
}

class GenerexHelper extends HandlebarsHelper<Object> {
    @Override
    public Object apply(Object o, Options options) throws IOException {
        Generex generex = new Generex((String) o);
        return generex.random();
    }
}