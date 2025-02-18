PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE VIEW `TimeTableView` AS select
    timetable.table_id,
    timetable.day_of_week,
    timetable.start_time,
    timetable.end_time
    timetable.period,
    subjects.subject_name,
    instructors.instructor_name
from timetable inner join subjects
on timetable.subject_id = subjects.subject_id
inner join instructors
on instructors.instructor_id = timetable.instructor_id;
COMMIT;