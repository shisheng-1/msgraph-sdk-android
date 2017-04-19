package com.microsoft.graph.functional;

import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.Suppress;

import com.google.gson.JsonPrimitive;
//import com.microsoft.graph.extensions.Extension;
//import com.microsoft.graph.extensions.IUserGetMailTipsCollectionPage;
//import com.microsoft.graph.extensions.MailTips;
//import com.microsoft.graph.extensions.MailTipsType;
//import com.microsoft.graph.extensions.RecipientScopeType;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

@Suppress
public class ODataTests extends AndroidTestCase {

    private TestBase testBase;

    @Before
    public void setUp() {
//        testBase = new TestBase();
//
//        // There's a current limitation of two extensions per user. If there's two extensions in the collection,
//        // delete the latest one
//        List<Extension> extensions = testBase.graphClient.getMe().getExtensions().buildRequest().get().getCurrentPage();
//        if (extensions.size() >= 2) {
//            testBase.graphClient.getMe().getExtensions(extensions.get(1).id).buildRequest().delete();
//        }
    }

    @Test
    public void testOpenExtensions() {
//        Extension extension = new Extension();
//
//        extension.getAdditionalDataManager().put("theme", new JsonPrimitive("dark"));
//        extension.getAdditionalDataManager().put("extensionName", new JsonPrimitive("Extension 1"));
//
//        Extension newExtension = testBase.graphClient.getMe().getExtensions().buildRequest().post(extension);
//
//        assertEquals(extension.getAdditionalDataManager().get("theme"), newExtension.getAdditionalDataManager().get("theme"));
//
//        testBase.graphClient.getMe().getExtensions(newExtension.id).buildRequest().delete();
    }

    @Test
    public void testEnumFlags() {
//        EnumSet<MailTipsType> mailTips = EnumSet.noneOf(MailTipsType.class);
//        mailTips.add(MailTipsType.automaticReplies);
//        mailTips.add(MailTipsType.customMailTip);
//        mailTips.add(MailTipsType.recipientScope);
//        List<String> emailAddresses = new ArrayList<String>();
//        emailAddresses.add("katiej@mod810997.onmicrosoft.com");
//        emailAddresses.add("garthf@mod810997.onmicrosoft.com");
//        emailAddresses.add("admin@mod810997.onmicrosoft.com");
//
//        IUserGetMailTipsCollectionPage mailTipsCollection = testBase.graphClient.getMe().getGetMailTips(emailAddresses, mailTips).buildRequest().post();
//
//        assertNotNull(mailTipsCollection);
//
//        List<MailTips> mailTipsPage = mailTipsCollection.getCurrentPage();
//        EnumSet<RecipientScopeType> recipientScopeTypes = EnumSet.allOf(RecipientScopeType.class);
//        for (int i = 0; i< mailTipsPage.size(); i++) {
//            if (mailTipsPage.get(i).recipientScope != null) {
//                assertTrue(mailTipsPage.get(i).recipientScope.getClass().isInstance(mailTips));
//            }
//        }
    }
}
