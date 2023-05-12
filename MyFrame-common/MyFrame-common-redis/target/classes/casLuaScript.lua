local key = KEYS[1]
local oldValue = ARGV[1]
local newValue = ARGV[2]

local curValue = redis.call("get",key)
if(curValue == false || tonumber(curValue) == tonumber(oldValue))
then
    redis.call("set",key,newValue)
else
    return false
end