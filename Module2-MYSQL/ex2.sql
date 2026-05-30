#User Upcoming Events
SELECT
    u.user_id,
    u.full_name,
    e.event_id,
    e.title,
    e.city,
    e.start_date
FROM users u
JOIN registrations r
    ON u.user_id = r.user_id
JOIN events e
    ON r.event_id = e.event_id
WHERE e.status = 'upcoming'
  AND u.city = e.city
ORDER BY e.start_date;
#Top Rated Events
SELECT e.event_id,
       e.title,
       AVG(f.rating) AS avg_rating,
       COUNT(f.feedback_id) AS feedback_count
FROM events e
JOIN feedback f
    ON e.event_id = f.event_id
GROUP BY e.event_id, e.title
HAVING COUNT(f.feedback_id) >= 10
   AND AVG(f.rating) = (
       SELECT MAX(avg_rating)
       FROM (
           SELECT AVG(rating) AS avg_rating
           FROM feedback
           GROUP BY event_id
           HAVING COUNT(feedback_id) >= 10
       ) t
   );
   #Inactive Users
   select u.user_id,u.full_name from users u
   left join registrations r on u.user_id=r.user_id and r.registration_date>=curdate()-interval 90 day;
   #Peak Session Hours
   select e.event_id,e.title,count(s.session_id) from events e
   join sessions s on e.event_id=s.event_id where time(s.start_time)>='10:00:00'
   and time(s.start_time)<='12:00:00' group by e.event_id;
   #Most Active Cities
   select u.city,count(distinct r.user_id) as tr from users u
   join registrations r on u.user_id=r.user_id group by u.city order by tr DESC limit 5;
   #Event Resource Summary
	select e.event_id,e.title,count(case when r.resource_type='pdf' then 1 end) as pdf_count,
       count(case when r.resource_type='image' then 1 end) as image_count,
       count(case when r.resource_type='link' then 1 end) as link_count
	from events e
	left join resources r
	on r.event_id=e.event_id
	group by e.event_id,e.title;
#Low Feedback Alerts
select u.user_id,
       u.full_name,
       f.comments,
       e.title
from users u
join feedback f
on u.user_id=f.user_id
join events e
on e.event_id=f.event_id
where f.rating<3;
#Sessions per Upcoming Event
select e.event_id,
       e.title,
       count(s.session_id) as session_count
from events e
left join sessions s
on e.event_id=s.event_id
where e.status='upcoming'
group by e.event_id,e.title;
#Organizer Event Summary
select u.user_id,
       u.full_name,
       e.status,
       count(e.event_id) as event_count
from users u
join events e
on u.user_id=e.organizer_id
group by u.user_id,u.full_name,e.status;
#Feedback Gap
select e.event_id,
       e.title
from events e
join registrations r
on e.event_id=r.event_id
left join feedback f
on e.event_id=f.event_id
where f.feedback_id is null
group by e.event_id,e.title;