package com.bot.gobot;


import com.bot.gobot.img.CreatePixelArrayFromKifu;
import com.bot.gobot.img.ImageFromIntArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class test {


    String so;
    String sso;

    @Autowired
    public test( @Value("${discord.applicationid}")String so,
                 @Value("${discord.publickey}")String sso) {
        this.so = so;
        this.sso = sso;
        run();
    }

    public void run() {
        System.out.println(so);
        System.out.println(sso);

        CreatePixelArrayFromKifu createPixelArrayFromKifu = new CreatePixelArrayFromKifu();
        ImageFromIntArray.generate(createPixelArrayFromKifu.createBigArrayFromGobanAndPictures());

    }
}
