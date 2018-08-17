#import "NotificationsProxy.h"

@implementation NotificationsProxy

- (NSDictionary*) checkValidNotification:(NSDictionary*)notificationData {
    
    NSDictionary* aps = [notificationData objectForKey:@"aps"];
    NSString* notificationsCategory = [aps objectForKey:@"category"];
    NSString* gameReminderNotificationTag = @"GameReminder";
    
    if([gameReminderNotificationTag isEqualToString:notificationsCategory]) {
        NSMutableDictionary *mutableNotificationData = [notificationData mutableCopy];
        NSMutableDictionary* mutableAps = [aps mutableCopy];
        NSMutableDictionary* mutableAlert = [[aps objectForKey:@"alert"] mutableCopy];
        [mutableAlert setObject:@"Holaaaa 123" forKey:@"body"];
        [mutableAps setObject:mutableAlert forKey:@"alert"];
        [mutableNotificationData setObject:mutableAps forKey:@"aps"];
        return mutableNotificationData;
    }
    return notificationData;
}

@end
